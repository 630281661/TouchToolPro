package top.bogey.touch_tool.ui.card.pin;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import top.bogey.touch_tool.data.action.BaseAction;
import top.bogey.touch_tool.data.pin.Pin;
import top.bogey.touch_tool.databinding.PinTopBinding;
import top.bogey.touch_tool.ui.card.BaseCard;

@SuppressLint("ViewConstructor")
public class PinTopView extends PinBaseView<PinTopBinding> {
    public PinTopView(@NonNull Context context, BaseCard<? extends BaseAction> card, Pin pin) {
        super(context, PinTopBinding.class, card, pin);
    }

    @Override
    public int[] getSlotLocationOnScreen(float scale) {
        int[] location = new int[2];
        pinSlot.getLocationOnScreen(location);
        location[0] += (pinSlot.getWidth() * scale / 2);
        return location;
    }

    @Override
    public void setValueView() {

    }
}
