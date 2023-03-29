package top.bogey.touch_tool.data.action.start;

import com.google.gson.JsonObject;

import top.bogey.touch_tool.R;
import top.bogey.touch_tool.data.TaskRunnable;
import top.bogey.touch_tool.data.WorldState;
import top.bogey.touch_tool.data.action.ActionContext;
import top.bogey.touch_tool.data.pin.Pin;
import top.bogey.touch_tool.data.pin.object.PinValueArea;

public class BatteryStartAction extends StartAction {
    private transient Pin areaPin = new Pin(new PinValueArea(1, 100, 1), R.string.action_battery_start_subtitle_battery);
    private transient boolean inRange = false;

    public BatteryStartAction() {
        super(R.string.action_battery_start_title);
        areaPin = addPin(areaPin);
    }

    public BatteryStartAction(JsonObject jsonObject) {
        super(R.string.action_battery_start_title, jsonObject);
        areaPin = reAddPin(areaPin);
    }

    @Override
    public boolean checkReady(TaskRunnable runnable, ActionContext actionContext) {
        WorldState worldState = WorldState.getInstance();
        int batteryPercent = worldState.getBatteryPercent();
        PinValueArea valueArea = (PinValueArea) getPinValue(runnable, actionContext, areaPin);
        int low = valueArea.getCurrMin();
        int high = valueArea.getCurrMax();
        boolean result = batteryPercent >= low && batteryPercent <= high;
        // 已经执行过了，电量未出范围不再重复执行
        if (result && inRange) return false;
        inRange = result;
        return inRange;
    }
}
