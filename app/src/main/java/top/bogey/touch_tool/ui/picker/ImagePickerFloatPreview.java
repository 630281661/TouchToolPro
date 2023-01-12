package top.bogey.touch_tool.ui.picker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import top.bogey.touch_tool.MainApplication;
import top.bogey.touch_tool.R;
import top.bogey.touch_tool.data.pin.object.PinColor;
import top.bogey.touch_tool.data.pin.object.PinImage;
import top.bogey.touch_tool.data.pin.object.PinValue;
import top.bogey.touch_tool.databinding.FloatPickerImagePreviewBinding;
import top.bogey.touch_tool.utils.DisplayUtils;
import top.bogey.touch_tool.utils.easy_float.EasyFloat;

@SuppressLint("ViewConstructor")
public class ImagePickerFloatPreview extends BasePickerFloatView {

    private PinImage pinImage;
    private PinColor pinColor;

    public ImagePickerFloatPreview(@NonNull Context context, PickerCallback callback, PinValue pinValue) {
        super(context, callback);
        boolean isImage = pinValue instanceof PinImage;

        FloatPickerImagePreviewBinding binding = FloatPickerImagePreviewBinding.inflate(LayoutInflater.from(context), this, true);

        if (isImage) {
            PinImage image = (PinImage) pinValue;
            pinImage = new PinImage(context, image.getScaleBitmap(context));
            binding.current.setImageBitmap(pinImage.getBitmap());
            binding.title.setText(R.string.picker_image_preview_title);
            binding.pickerButton.setIconResource(R.drawable.icon_action_image);
            binding.pickerButton.setOnClickListener(v -> new ImagePickerFloatView(context, () -> binding.current.setImageBitmap(pinImage.getBitmap()), pinImage).show());
        } else {
            pinColor = new PinColor((PinColor) pinValue);
            binding.current.setBackgroundColor(DisplayUtils.getColorFromHsv(pinColor.getColor()));
            binding.title.setText(R.string.picker_color_preview_title);
            binding.pickerButton.setIconResource(R.drawable.icon_action_color);
            binding.pickerButton.setOnClickListener(v -> new ColorPickerFloatView(context, () -> binding.current.setBackgroundColor(DisplayUtils.getColorFromHsv(pinColor.getColor())), pinColor).show());
        }

        binding.saveButton.setOnClickListener(v -> {
            if (callback != null) {
                if (isImage) {
                    if (pinImage != null) {
                        PinImage image = (PinImage) pinValue;
                        Bitmap bitmap = pinImage.getBitmap();
                        image.setBitmap(context, bitmap);
                    }
                } else {
                    if (pinColor != null) {
                        PinColor color = (PinColor) pinValue;
                        int[] colorValue = pinColor.getColor();
                        color.setColor(colorValue);
                        color.setMinSize(pinColor.getMinSize());
                        color.setMaxSize(pinColor.getMaxSize());
                        color.setScreen(DisplayUtils.getScreen(context));
                    }
                }
                callback.onComplete();
            }
            dismiss();
        });

        binding.backButton.setOnClickListener(v -> dismiss());
    }

    @Override
    public void show() {
        EasyFloat.with(MainApplication.getService())
                .setLayout(this)
                .setTag(tag)
                .setDragEnable(true)
                .setCallback(floatCallback)
                .setAnimator(null)
                .show();
    }
}