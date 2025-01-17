package top.bogey.touch_tool_pro.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.HashSet;

import top.bogey.touch_tool_pro.R;
import top.bogey.touch_tool_pro.bean.base.SaveRepository;
import top.bogey.touch_tool_pro.bean.task.Task;
import top.bogey.touch_tool_pro.databinding.ViewTagListBinding;
import top.bogey.touch_tool_pro.databinding.ViewTagListItemBinding;
import top.bogey.touch_tool_pro.utils.AppUtils;

public class TaskTagView extends BottomSheetDialogFragment {
    private final TaskView taskView;
    private final HashSet<String> commonTags = new HashSet<>();
    private ViewTagListBinding binding;

    public TaskTagView(TaskView taskView) {
        this.taskView = taskView;
        if (taskView.isSelect) {
            HashSet<String> tags = null;
            for (Task value : taskView.selectedTasks.values()) {
                if (tags == null) {
                    tags = new HashSet<>();
                    if (value.getTags() != null) tags.addAll(value.getTags());
                } else {
                    if (value.getTags() != null) tags.removeIf(tag -> !value.getTags().contains(tag));
                }
            }
            if (tags != null) commonTags.addAll(tags);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewTagListBinding.inflate(inflater, container, false);

        binding.addButton.setOnClickListener(v -> AppUtils.showEditDialog(requireContext(), R.string.tag_add, null, result -> {
            if (result != null && result.length() > 0) {
                ArrayList<String> tags = SaveRepository.getInstance().getTaskTags();
                if (tags.contains(result.toString())) return;
                SaveRepository.getInstance().addTaskTag(result.toString());
                binding.tagBox.removeAllViews();
                SaveRepository.getInstance().getTaskTags().forEach(this::addTagChip);
            }
        }));

        SaveRepository.getInstance().getTaskTags().forEach(this::addTagChip);

        return binding.getRoot();
    }

    private void addTagChip(String tag) {
        ViewTagListItemBinding itemBinding = ViewTagListItemBinding.inflate(LayoutInflater.from(getContext()), binding.tagBox, false);
        binding.tagBox.addView(itemBinding.getRoot());
        Chip chip = itemBinding.getRoot();
        chip.setText(tag);
        chip.setOnCloseIconClickListener(v -> AppUtils.showDialog(getContext(), R.string.tag_delete, result -> {
            if (result) {
                SaveRepository.getInstance().removeTaskTag(tag);
                binding.tagBox.removeView(chip);
            }
        }));
        chip.setCloseIconVisible(!SaveRepository.SHORTCUT_TAG.equals(tag));

        if (taskView.isSelect) {
            chip.setCheckable(true);
            chip.setChecked(commonTags.contains(tag));
            chip.setOnClickListener(v -> {
                if (commonTags.contains(tag)) {
                    commonTags.remove(tag);
                    taskView.selectedTasks.values().forEach(task -> task.removeTag(tag));
                } else {
                    commonTags.add(tag);
                    taskView.selectedTasks.values().forEach(task -> task.addTag(tag));
                }
            });
        } else {
            chip.setCheckable(false);
            chip.setChecked(false);
        }
    }

    @Override
    public void onDestroy() {
        taskView.selectedTasks.forEach((id, task) -> task.save());
        taskView.unSelectAll();
        taskView.hideBottomBar();
        super.onDestroy();
    }
}
