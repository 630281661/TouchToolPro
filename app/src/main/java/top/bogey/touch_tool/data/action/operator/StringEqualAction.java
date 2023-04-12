package top.bogey.touch_tool.data.action.operator;

import com.google.gson.JsonObject;

import java.util.Objects;

import top.bogey.touch_tool.R;
import top.bogey.touch_tool.data.TaskRunnable;
import top.bogey.touch_tool.data.action.ActionContext;
import top.bogey.touch_tool.data.action.CalculateAction;
import top.bogey.touch_tool.data.pin.Pin;
import top.bogey.touch_tool.data.pin.PinDirection;
import top.bogey.touch_tool.data.pin.object.PinBoolean;
import top.bogey.touch_tool.data.pin.object.PinString;

public class StringEqualAction extends CalculateAction {
    protected transient Pin outValuePin = new Pin(new PinBoolean(), PinDirection.OUT);
    protected transient Pin originPin = new Pin(new PinString());
    protected transient Pin secondPin = new Pin(new PinString());

    public StringEqualAction() {
        super(R.string.action_string_equal_operator_title);
        outValuePin = addPin(outValuePin);
        originPin = addPin(originPin);
        secondPin = addPin(secondPin);
    }

    public StringEqualAction(JsonObject jsonObject) {
        super(R.string.action_string_equal_operator_title, jsonObject);
        outValuePin = reAddPin(outValuePin);
        originPin = reAddPin(originPin);
        secondPin = reAddPin(secondPin);
    }

    @Override
    protected void calculatePinValue(TaskRunnable runnable, ActionContext actionContext, Pin pin) {
        if (!pin.getId().equals(outValuePin.getId())) return;
        PinBoolean value = (PinBoolean) outValuePin.getValue();
        value.setValue(false);

        PinString origin = (PinString) getPinValue(runnable, actionContext, originPin);
        PinString second = (PinString) getPinValue(runnable, actionContext, secondPin);
        value.setValue(Objects.equals(origin.getValue(), second.getValue()));
    }
}
