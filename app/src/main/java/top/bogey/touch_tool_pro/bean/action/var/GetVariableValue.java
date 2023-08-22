package top.bogey.touch_tool_pro.bean.action.var;

import com.google.gson.JsonObject;

import top.bogey.touch_tool_pro.bean.action.Action;
import top.bogey.touch_tool_pro.bean.action.ActionType;
import top.bogey.touch_tool_pro.bean.function.FunctionContext;
import top.bogey.touch_tool_pro.bean.pin.Pin;
import top.bogey.touch_tool_pro.bean.pin.pins.PinValue;
import top.bogey.touch_tool_pro.bean.task.TaskRunnable;
import top.bogey.touch_tool_pro.utils.GsonUtils;

public class GetVariableValue extends Action {
    private final String varKey;
    private final transient Pin valuePin;

    public GetVariableValue(String varKey, PinValue value) {
        super(ActionType.VAR_GET);
        this.varKey = varKey;
        valuePin = addPin(new Pin(value, true));
        valuePin.setTitle(varKey);
    }

    public GetVariableValue(JsonObject jsonObject) {
        super(jsonObject);
        varKey = GsonUtils.getAsString(jsonObject, "varKey", null);
        valuePin = addPin(tmpPins.remove(0));
    }

    @Override
    public void calculate(TaskRunnable runnable, FunctionContext context, Pin pin) {
        PinValue value = context.findVar(varKey);
        if (value == null) return;
        valuePin.setValue(value);
    }

    @Override
    public boolean check(FunctionContext context) {
        PinValue value = context.findVar(varKey);
        return value != null;
    }

    public String getVarKey() {
        return varKey;
    }

    public void setValue(PinValue value) {
        valuePin.setValue(value);
    }
}