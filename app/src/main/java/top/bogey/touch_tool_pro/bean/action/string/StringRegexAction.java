package top.bogey.touch_tool_pro.bean.action.string;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import top.bogey.touch_tool_pro.R;
import top.bogey.touch_tool_pro.bean.action.ActionType;
import top.bogey.touch_tool_pro.bean.action.check.CheckAction;
import top.bogey.touch_tool_pro.bean.function.FunctionContext;
import top.bogey.touch_tool_pro.bean.pin.Pin;
import top.bogey.touch_tool_pro.bean.pin.pins.PinAdd;
import top.bogey.touch_tool_pro.bean.pin.pins.PinBoolean;
import top.bogey.touch_tool_pro.bean.pin.pins.PinString;
import top.bogey.touch_tool_pro.bean.task.TaskRunnable;

public class StringRegexAction extends CheckAction {
    private transient Pin textPin = new Pin(new PinString(), R.string.pin_string);
    private transient Pin matchPin = new Pin(new PinString(), R.string.action_string_regex_subtitle_match);
    private final transient Pin morePin = new Pin(new PinString(), R.string.action_string_regex_subtitle_match_result, true);
    private transient Pin addPin = new Pin(new PinAdd(morePin), R.string.action_subtitle_add_pin, true);
    private final transient ArrayList<Pin> stringPins = new ArrayList<>();

    public StringRegexAction() {
        super(ActionType.STRING_REGEX);
        textPin = addPin(textPin);
        matchPin = addPin(matchPin);
        addPin = addPin(addPin);
    }

    public StringRegexAction(JsonObject jsonObject) {
        super(jsonObject);
        textPin = reAddPin(textPin);
        matchPin = reAddPin(matchPin);
        stringPins.addAll(reAddPin(morePin, 1));
        addPin = reAddPin(addPin);
    }

    @Override
    public void calculate(TaskRunnable runnable, FunctionContext context, Pin pin) {
        PinBoolean result = resultPin.getValue(PinBoolean.class);
        result.setBool(false);
        for (Pin stringPin : stringPins) {
            stringPin.getValue(PinString.class).setValue(null);
        }

        PinString text = (PinString) getPinValue(runnable, context, textPin);
        if (text.getValue() == null || text.getValue().isEmpty()) return;

        PinString match = (PinString) getPinValue(runnable, context, matchPin);
        if (match.getValue() == null || match.getValue().isEmpty()) return;

        Pattern pattern = Pattern.compile(match.getValue());
        Matcher matcher = pattern.matcher(text.getValue());
        if (matcher.find()) {
            result.setBool(true);
            for (int i = 1; i <= matcher.groupCount() ; i++) {
                int index = i - 1;
                if (stringPins.size() > index) {
                    Pin stringPin = stringPins.get(index);
                    stringPin.getValue(PinString.class).setValue(matcher.group(i));
                }
            }
        }
    }
}
