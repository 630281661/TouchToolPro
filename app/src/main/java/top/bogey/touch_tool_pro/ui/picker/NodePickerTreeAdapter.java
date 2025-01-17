package top.bogey.touch_tool_pro.ui.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeNodeManager;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolder;

import java.util.ArrayList;
import java.util.List;

import top.bogey.touch_tool_pro.R;
import top.bogey.touch_tool_pro.databinding.FloatPickerNodeItemBinding;
import top.bogey.touch_tool_pro.utils.DisplayUtils;

public class NodePickerTreeAdapter extends TreeViewAdapter {
    private TreeNode selectedNode;
    private final ArrayList<TreeNode> treeNodes = new ArrayList<>();
    private final TreeNodeManager manager;

    public NodePickerTreeAdapter(TreeNodeManager manager, SelectNode picker) {
        super(null, manager);
        this.manager = manager;
        setTreeNodeLongClickListener((treeNode, view) -> {
            AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) treeNode.getValue();
            picker.selectNode(nodeInfo);
            selectedNode = treeNode;
//            notifyDataSetChanged();
            return true;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ((ViewHolder) holder).refreshItem(manager.get(position));
    }

    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int layoutId) {
        return new ViewHolder(FloatPickerNodeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void setRoots(ArrayList<AccessibilityNodeInfo> roots) {
        treeNodes.clear();
        for (AccessibilityNodeInfo root : roots) {
            TreeNode rootNode = createTree(root, 0);
            treeNodes.add(rootNode);
        }
        updateTreeNodes(treeNodes);
    }

    private TreeNode createTree(AccessibilityNodeInfo root, int level) {
        TreeNode node = new TreeNode(root, R.layout.float_picker_node_item);
        node.setLevel(level);
        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                node.addChild(createTree(child, level + 1));
            }
        }
        return node;
    }

    public void setSelectedNode(AccessibilityNodeInfo node) {
        collapseAll();
        if (node == null) {
            selectedNode = null;
        } else {
            selectedNode = findTreeNode(treeNodes, node);
            if (selectedNode != null) {
                TreeNode parent = selectedNode.getParent();
                while (parent != null) {
                    TreeNode p = parent.getParent();
                    if (p == null) {
                        parent.setExpanded(false);
                        expandNode(parent);
                    } else {
                        parent.setExpanded(true);
                    }
                    parent = p;
                }
            }
        }
    }

    private TreeNode findTreeNode(List<TreeNode> treeNodes, Object value) {
        for (TreeNode treeNode : treeNodes) {
            if (value.equals(treeNode.getValue())) return treeNode;
            TreeNode node = findTreeNode(treeNode.getChildren(), value);
            if (node != null) return node;
        }
        return null;
    }

    protected class ViewHolder extends TreeViewHolder {
        private final FloatPickerNodeItemBinding binding;
        private final Context context;

        public ViewHolder(@NonNull FloatPickerNodeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        @Override
        public void bindTreeNode(TreeNode node) {
            int padding = (int) (node.getLevel() * DisplayUtils.dp2px(context, 8));
            binding.contentBox.setPaddingRelative(padding, 0, 0, 0);
        }

        public void refreshItem(TreeNode node) {
            AccessibilityNodeInfo value = (AccessibilityNodeInfo) node.getValue();
            binding.titleText.setText(getNodeTitle(value));

            int color;
            if (value.isClickable()) {
                color = DisplayUtils.getAttrColor(context, com.google.android.material.R.attr.colorPrimary, 0);
            } else {
                color = DisplayUtils.getAttrColor(context, com.google.android.material.R.attr.colorOnSurface, 0);
            }
            binding.titleText.setTextColor(color);
            binding.imageView.setImageTintList(ColorStateList.valueOf(color));

            binding.imageView.setVisibility(node.getChildren().size() > 0 ? View.VISIBLE : View.INVISIBLE);
            binding.imageView.setImageResource(node.isExpanded() ? R.drawable.icon_up : R.drawable.icon_down);

            if (node.equals(selectedNode)) {
                binding.titleText.setTextColor(DisplayUtils.getAttrColor(context, com.google.android.material.R.attr.colorError, 0));
            }
        }

        private String getNodeTitle(AccessibilityNodeInfo node) {
            StringBuilder builder = new StringBuilder();
            builder.append(node.getClassName());
            CharSequence text = node.getText();
            if (text != null && text.length() > 0) {
                builder.append(" | ");
                builder.append(text);
            }

            String resourceName = node.getViewIdResourceName();
            if (resourceName != null && !resourceName.isEmpty()) {
                String[] split = resourceName.split(":");
                if (split.length > 1) {
                    builder.append(" [ ");
                    builder.append(split[1]);
                    builder.append(" ]");
                } else {
                    builder.append(resourceName);
                }
            }

            return builder.toString();
        }
    }

    public interface SelectNode {
        void selectNode(AccessibilityNodeInfo nodeInfo);
    }
}