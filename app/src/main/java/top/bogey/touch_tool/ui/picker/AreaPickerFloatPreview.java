package top.bogey.touch_tool.ui.picker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import top.bogey.touch_tool.MainApplication;
import top.bogey.touch_tool.data.pin.object.PinArea;
import top.bogey.touch_tool.databinding.FloatPickerAreaPreviewBinding;
import top.bogey.touch_tool.utils.easy_float.EasyFloat;

@SuppressLint("ViewConstructor")
public class AreaPickerFloatPreview extends BasePickerFloatView {
    private final FloatPickerAreaPreviewBinding binding;
    private final PinArea newPinArea;

    @SuppressLint("DefaultLocale")
    public AreaPickerFloatPreview(@NonNull Context context, PickerCallback callback, PinArea pinArea) {
        super(context, callback);
        newPinArea = (PinArea) pinArea.copy();

        binding = FloatPickerAreaPreviewBinding.inflate(LayoutInflater.from(context), this, true);
        Rect area = pinArea.getArea(context);
        binding.leftEdit.setText(String.valueOf(area.left));
        binding.topEdit.setText(String.valueOf(area.top));
        binding.rightEdit.setText(String.valueOf(area.right));
        binding.bottomEdit.setText(String.valueOf(area.bottom));

        binding.pickerButton.setOnClickListener(v -> {
            setNewPinArea();
            new AreaPickerFloatView(context, () -> {
                Rect rect = newPinArea.getArea(context);
                binding.leftEdit.setText(String.valueOf(rect.left));
                binding.topEdit.setText(String.valueOf(rect.top));
                binding.rightEdit.setText(String.valueOf(rect.right));
                binding.bottomEdit.setText(String.valueOf(rect.bottom));
            }, newPinArea).show();
        });

        binding.saveButton.setOnClickListener(v -> {
            setNewPinArea();
            if (callback != null) {
                pinArea.setArea(context, newPinArea);
                callback.onComplete();
            }
            dismiss();
        });

        binding.backButton.setOnClickListener(v -> dismiss());
    }

    private void setNewPinArea() {
        Editable left = binding.leftEdit.getText();
        if (left != null && left.length() > 0) newPinArea.left = Integer.parseInt(left.toString());
        Editable top = binding.topEdit.getText();
        if (top != null && top.length() > 0) newPinArea.top = Integer.parseInt(top.toString());
        Editable right = binding.rightEdit.getText();
        if (right != null && right.length() > 0) newPinArea.right = Integer.parseInt(right.toString());
        Editable bottom = binding.bottomEdit.getText();
        if (bottom != null && bottom.length() > 0) newPinArea.bottom = Integer.parseInt(bottom.toString());
    }

    @Override
    public void show() {
        EasyFloat.with(MainApplication.getInstance().getService())
                .setLayout(this)
                .setTag(tag)
                .setDragEnable(true)
                .setCallback(floatCallback)
                .setAnimator(null)
                .hasEditText(true)
                .show();
    }
}
