package top.bogey.touch_tool_pro.bean.action;

import androidx.annotation.DrawableRes;

import top.bogey.touch_tool_pro.MainApplication;
import top.bogey.touch_tool_pro.R;
import top.bogey.touch_tool_pro.bean.action.bool.BoolAndAction;
import top.bogey.touch_tool_pro.bean.action.bool.BoolNotAction;
import top.bogey.touch_tool_pro.bean.action.bool.BoolOrAction;
import top.bogey.touch_tool_pro.bean.action.check.ColorEqualAction;
import top.bogey.touch_tool_pro.bean.action.check.ExistColorAction;
import top.bogey.touch_tool_pro.bean.action.check.ExistImageAction;
import top.bogey.touch_tool_pro.bean.action.check.ExistNodeAction;
import top.bogey.touch_tool_pro.bean.action.check.ExistTextAction;
import top.bogey.touch_tool_pro.bean.action.check.ImageContainAction;
import top.bogey.touch_tool_pro.bean.action.check.InAppCheckAction;
import top.bogey.touch_tool_pro.bean.action.check.OnBatteryStateAction;
import top.bogey.touch_tool_pro.bean.action.check.OnScreenStateAction;
import top.bogey.touch_tool_pro.bean.action.function.FunctionEndAction;
import top.bogey.touch_tool_pro.bean.action.function.FunctionPinsAction;
import top.bogey.touch_tool_pro.bean.action.function.FunctionReferenceAction;
import top.bogey.touch_tool_pro.bean.action.function.FunctionStartAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntAddAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntDivAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntEqualAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntInAreaAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntLargeAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntModAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntMultiAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntRandomAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntReduceAction;
import top.bogey.touch_tool_pro.bean.action.integer.IntSmallAction;
import top.bogey.touch_tool_pro.bean.action.logic.ForLogicAction;
import top.bogey.touch_tool_pro.bean.action.logic.IfLogicAction;
import top.bogey.touch_tool_pro.bean.action.logic.ParallelAction;
import top.bogey.touch_tool_pro.bean.action.logic.RandomAction;
import top.bogey.touch_tool_pro.bean.action.logic.SequenceAction;
import top.bogey.touch_tool_pro.bean.action.logic.WaitIfLogicAction;
import top.bogey.touch_tool_pro.bean.action.logic.WhileLogicAction;
import top.bogey.touch_tool_pro.bean.action.normal.BreakTaskAction;
import top.bogey.touch_tool_pro.bean.action.normal.CaptureSwitchAction;
import top.bogey.touch_tool_pro.bean.action.normal.ClickKeyAction;
import top.bogey.touch_tool_pro.bean.action.normal.ClickNodeAction;
import top.bogey.touch_tool_pro.bean.action.normal.ClickPositionAction;
import top.bogey.touch_tool_pro.bean.action.normal.CopyToClipboardAction;
import top.bogey.touch_tool_pro.bean.action.normal.DelayAction;
import top.bogey.touch_tool_pro.bean.action.normal.InputAction;
import top.bogey.touch_tool_pro.bean.action.normal.LogAction;
import top.bogey.touch_tool_pro.bean.action.normal.OpenAppAction;
import top.bogey.touch_tool_pro.bean.action.normal.OpenUriAction;
import top.bogey.touch_tool_pro.bean.action.normal.PlayRingtoneAction;
import top.bogey.touch_tool_pro.bean.action.normal.RunTaskAction;
import top.bogey.touch_tool_pro.bean.action.normal.ScreenSwitchAction;
import top.bogey.touch_tool_pro.bean.action.normal.TouchAction;
import top.bogey.touch_tool_pro.bean.action.pos.PosFromIntAction;
import top.bogey.touch_tool_pro.bean.action.pos.PosInAreaAction;
import top.bogey.touch_tool_pro.bean.action.pos.PosOffsetAction;
import top.bogey.touch_tool_pro.bean.action.pos.PosToAreaAction;
import top.bogey.touch_tool_pro.bean.action.pos.PosToIntAction;
import top.bogey.touch_tool_pro.bean.action.pos.PosToTouchAction;
import top.bogey.touch_tool_pro.bean.action.start.AppStartAction;
import top.bogey.touch_tool_pro.bean.action.start.BatteryStartAction;
import top.bogey.touch_tool_pro.bean.action.start.InnerStartAction;
import top.bogey.touch_tool_pro.bean.action.start.ManualStartAction;
import top.bogey.touch_tool_pro.bean.action.start.NotifyStartAction;
import top.bogey.touch_tool_pro.bean.action.start.OuterStartAction;
import top.bogey.touch_tool_pro.bean.action.start.TimeStartAction;
import top.bogey.touch_tool_pro.bean.action.state.AppStateAction;
import top.bogey.touch_tool_pro.bean.action.state.BatteryStateAction;
import top.bogey.touch_tool_pro.bean.action.state.CaptureStateAction;
import top.bogey.touch_tool_pro.bean.action.state.ColorStateAction;
import top.bogey.touch_tool_pro.bean.action.state.DateStateAction;
import top.bogey.touch_tool_pro.bean.action.state.ImageStateAction;
import top.bogey.touch_tool_pro.bean.action.state.ScreenStateAction;
import top.bogey.touch_tool_pro.bean.action.state.TimeStateAction;
import top.bogey.touch_tool_pro.bean.action.string.StringAddAction;
import top.bogey.touch_tool_pro.bean.action.string.StringEqualAction;
import top.bogey.touch_tool_pro.bean.action.string.StringFromValueAction;
import top.bogey.touch_tool_pro.bean.action.string.StringRegexAction;
import top.bogey.touch_tool_pro.bean.action.string.StringToIntAction;
import top.bogey.touch_tool_pro.bean.action.var.GetCommonVariableValue;
import top.bogey.touch_tool_pro.bean.action.var.GetVariableValue;
import top.bogey.touch_tool_pro.bean.action.var.SetCommonVariableValue;
import top.bogey.touch_tool_pro.bean.action.var.SetVariableValue;

public enum ActionType {
    BASE,

    CUSTOM,
    CUSTOM_START,
    CUSTOM_END,
    CUSTOM_PIN,

    VAR_GET,
    VAR_SET,
    COMMON_VAR_GET,
    COMMON_VAR_SET,

    MANUAL_START,
    ENTER_APP_START,
    TIME_START,
    NOTIFY_START,
    BATTERY_START,
    OUTER_START,
    NORMAL_START,
    INNER_START,

    LOGIC_IF,
    LOGIC_WAIT_IF,
    LOGIC_FOR,
    LOGIC_WHILE,
    LOGIC_SEQUENCE,
    LOGIC_RANDOM,
    LOGIC_PARALLEL,

    APP_STATE,
    BATTERY_STATE,
    SCREEN_STATE,
    CAPTURE_STATE,
    COLOR_STATE,
    IMAGE_STATE,
    DATE_STATE,
    TIME_STATE,

    CHECK_IN_APP,
    CHECK_ON_BATTERY_STATE,
    CHECK_ON_SCREEN_STATE,
    CHECK_EXIST_TEXT,
    CHECK_EXIST_NODE,
    CHECK_EXIST_IMAGE,
    CHECK_IMAGE,
    CHECK_EXIST_COLOR,
    CHECK_COLOR,

    DELAY,
    LOG,
    CLICK_POSITION,
    CLICK_NODE,
    CLICK_KEY,
    TOUCH,
    INPUT,
    SCREEN_SWITCH,
    CAPTURE_SWITCH,
    OPEN_APP,
    OPEN_URI,
    PLAY_RINGTONE,
    COPY,
    RUN_TASK,
    BREAK_TASK,

    BOOL_OR,
    BOOL_AND,
    BOOL_NOT,

    STRING_FROM_VALUE,
    STRING_TO_INT,
    STRING_ADD,
    STRING_EQUAL,
    STRING_REGEX,

    INT_ADD,
    INT_REDUCE,
    INT_MULTI,
    INT_DIV,
    INT_MOD,
    INT_EQUAL,
    INT_LARGE,
    INT_SMALL,
    INT_IN_AREA,
    INT_RANDOM,

    POS_FROM_INT,
    POS_TO_INT,
    POS_OFFSET,
    POS_IN_AREA,
    POS_TO_AREA,
    POS_TO_TOUCH
    ;

    public String getTitle() {
        int id = switch (this) {
            case CUSTOM_START -> R.string.function_start;
            case CUSTOM_END -> R.string.function_end;
            case VAR_GET -> R.string.action_get_value_action_title;
            case VAR_SET -> R.string.action_set_value_action_title;
            case COMMON_VAR_GET -> R.string.action_get_common_value_action_title;
            case COMMON_VAR_SET -> R.string.action_set_common_value_action_title;
            case MANUAL_START -> R.string.action_manual_start_title;
            case ENTER_APP_START -> R.string.action_app_start_title;
            case TIME_START -> R.string.action_time_start_title;
            case NOTIFY_START -> R.string.action_notification_start_title;
            case BATTERY_START -> R.string.action_battery_start_title;
            case OUTER_START -> R.string.action_outer_start_title;
            case INNER_START -> R.string.action_inner_start_title;

            case LOGIC_IF -> R.string.action_condition_logic_title;
            case LOGIC_WAIT_IF -> R.string.action_wait_condition_logic_title;
            case LOGIC_FOR -> R.string.action_for_loop_logic_title;
            case LOGIC_WHILE -> R.string.action_condition_while_logic_title;
            case LOGIC_SEQUENCE -> R.string.action_sequence_logic_title;
            case LOGIC_RANDOM -> R.string.action_random_logic_title;
            case LOGIC_PARALLEL -> R.string.action_parallel_logic_title;

            case APP_STATE -> R.string.action_app_state_title;
            case BATTERY_STATE -> R.string.action_battery_state_title;
            case SCREEN_STATE -> R.string.action_screen_state_title;
            case CAPTURE_STATE -> R.string.action_capture_state_title;
            case IMAGE_STATE -> R.string.action_image_state_title;
            case COLOR_STATE -> R.string.action_color_state_title;
            case DATE_STATE -> R.string.action_date_state_title;
            case TIME_STATE -> R.string.action_time_state_title;

            case CHECK_IN_APP -> R.string.action_in_app_check_title;
            case CHECK_ON_BATTERY_STATE -> R.string.action_battery_state_check_title;
            case CHECK_ON_SCREEN_STATE -> R.string.action_screen_state_check_title;
            case CHECK_EXIST_TEXT -> R.string.action_exist_text_check_title;
            case CHECK_EXIST_NODE -> R.string.action_exist_node_check_title;
            case CHECK_EXIST_IMAGE -> R.string.action_exist_image_check_title;
            case CHECK_EXIST_COLOR -> R.string.action_exist_color_check_title;
            case CHECK_IMAGE -> R.string.action_image_check_title;
            case CHECK_COLOR -> R.string.action_color_check_title;

            case DELAY -> R.string.action_delay_action_title;
            case LOG -> R.string.action_log_action_title;
            case CLICK_POSITION -> R.string.action_touch_pos_action_title;
            case CLICK_NODE -> R.string.action_touch_node_action_title;
            case CLICK_KEY -> R.string.action_system_ability_action_title;
            case TOUCH -> R.string.action_touch_path_action_title;
            case INPUT -> R.string.action_input_node_action_title;
            case SCREEN_SWITCH -> R.string.action_screen_action_title;
            case CAPTURE_SWITCH -> R.string.action_open_capture_action_title;
            case OPEN_APP -> R.string.action_open_app_action_title;
            case OPEN_URI -> R.string.action_open_url_action_title;
            case PLAY_RINGTONE -> R.string.action_play_ringtone_action_title;
            case COPY -> R.string.action_copy_action_title;
            case RUN_TASK -> R.string.action_do_task_action_title;
            case BREAK_TASK -> R.string.action_break_task_action_title;

            case BOOL_OR -> R.string.action_bool_convert_or_title;
            case BOOL_AND -> R.string.action_bool_convert_and_title;
            case BOOL_NOT -> R.string.action_bool_convert_not_title;

            case STRING_FROM_VALUE -> R.string.action_string_from_value_title;
            case STRING_TO_INT -> R.string.action_string_to_int_title;
            case STRING_ADD -> R.string.action_string_add_title;
            case STRING_EQUAL -> R.string.action_string_equal_title;
            case STRING_REGEX -> R.string.action_string_regex_title;

            case INT_ADD -> R.string.action_int_add_title;
            case INT_REDUCE -> R.string.action_int_reduce_title;
            case INT_MULTI -> R.string.action_int_multi_title;
            case INT_DIV -> R.string.action_int_div_title;
            case INT_MOD -> R.string.action_int_mod_title;
            case INT_EQUAL -> R.string.action_int_equal_title;
            case INT_LARGE -> R.string.action_int_large_title;
            case INT_SMALL -> R.string.action_int_small_title;
            case INT_IN_AREA -> R.string.action_int_in_area_title;
            case INT_RANDOM -> R.string.action_int_random_title;

            case POS_FROM_INT -> R.string.action_position_from_int_title;
            case POS_TO_INT -> R.string.action_position_to_int_title;
            case POS_OFFSET -> R.string.action_position_offset_title;
            case POS_IN_AREA -> R.string.action_position_in_area_title;
            case POS_TO_AREA -> R.string.action_position_to_area_title;
            case POS_TO_TOUCH -> R.string.action_position_to_touch_title;
            default -> 0;
        };
        if (id == 0) return "";
        return MainApplication.getInstance().getString(id);
    }

    public @DrawableRes int getIcon() {
        return switch (this) {
            case VAR_GET, COMMON_VAR_GET -> R.drawable.icon_get_value;
            case VAR_SET, COMMON_VAR_SET -> R.drawable.icon_set_value;
            case MANUAL_START -> R.drawable.icon_hand;
            case ENTER_APP_START -> R.drawable.icon_package_info;
            case TIME_START -> R.drawable.icon_time;
            case NOTIFY_START -> R.drawable.icon_notification;
            case BATTERY_START -> R.drawable.icon_battery;
            case OUTER_START -> R.drawable.icon_auto_start;

            case LOGIC_IF -> R.drawable.icon_condition;
            case LOGIC_WAIT_IF -> R.drawable.icon_wait_condition;
            case LOGIC_FOR -> R.drawable.icon_for_loop;
            case LOGIC_WHILE -> R.drawable.icon_condition_while;
            case LOGIC_SEQUENCE -> R.drawable.icon_sequence;
            case LOGIC_RANDOM -> R.drawable.icon_random;
            case LOGIC_PARALLEL -> R.drawable.icon_parallel;

            case APP_STATE -> R.drawable.icon_package_info;
            case BATTERY_STATE -> R.drawable.icon_battery;
            case SCREEN_STATE -> R.drawable.icon_screen;
            case CAPTURE_STATE -> R.drawable.icon_capture;
            case DATE_STATE -> R.drawable.icon_date;
            case TIME_STATE -> R.drawable.icon_time;

            case CHECK_IN_APP -> R.drawable.icon_package_info;
            case CHECK_ON_BATTERY_STATE -> R.drawable.icon_battery;
            case CHECK_ON_SCREEN_STATE -> R.drawable.icon_screen;
            case CHECK_EXIST_TEXT -> R.drawable.icon_text;
            case CHECK_EXIST_NODE -> R.drawable.icon_widget;
            case CHECK_EXIST_IMAGE, CHECK_IMAGE, IMAGE_STATE -> R.drawable.icon_image;
            case CHECK_EXIST_COLOR, CHECK_COLOR, COLOR_STATE -> R.drawable.icon_color;

            case DELAY -> R.drawable.icon_delay;
            case LOG -> R.drawable.icon_log;
            case CLICK_POSITION -> R.drawable.icon_position;
            case CLICK_NODE -> R.drawable.icon_widget;
            case CLICK_KEY -> R.drawable.icon_screen;
            case TOUCH -> R.drawable.icon_path;
            case INPUT -> R.drawable.icon_input;
            case SCREEN_SWITCH -> R.drawable.icon_screen;
            case CAPTURE_SWITCH -> R.drawable.icon_capture;
            case OPEN_APP -> R.drawable.icon_package_info;
            case OPEN_URI -> R.drawable.icon_uri;
            case PLAY_RINGTONE -> R.drawable.icon_notification;
            case COPY -> R.drawable.icon_copy;
            case RUN_TASK -> R.drawable.icon_task;
            case BREAK_TASK -> R.drawable.icon_stop;
            default -> 0;
        };
    }

    public Class<? extends Action> getActionClass() {
        return switch (this) {
            case CUSTOM -> FunctionReferenceAction.class;
            case CUSTOM_START -> FunctionStartAction.class;
            case CUSTOM_END -> FunctionEndAction.class;
            case CUSTOM_PIN -> FunctionPinsAction.class;
            case VAR_GET -> GetVariableValue.class;
            case VAR_SET -> SetVariableValue.class;
            case COMMON_VAR_GET -> GetCommonVariableValue.class;
            case COMMON_VAR_SET -> SetCommonVariableValue.class;
            case MANUAL_START -> ManualStartAction.class;
            case ENTER_APP_START -> AppStartAction.class;
            case TIME_START -> TimeStartAction.class;
            case NOTIFY_START -> NotifyStartAction.class;
            case BATTERY_START -> BatteryStartAction.class;
            case OUTER_START -> OuterStartAction.class;
            case INNER_START -> InnerStartAction.class;

            case LOGIC_IF -> IfLogicAction.class;
            case LOGIC_WAIT_IF -> WaitIfLogicAction.class;
            case LOGIC_FOR -> ForLogicAction.class;
            case LOGIC_WHILE -> WhileLogicAction.class;
            case LOGIC_SEQUENCE -> SequenceAction.class;
            case LOGIC_RANDOM -> RandomAction.class;
            case LOGIC_PARALLEL -> ParallelAction.class;

            case APP_STATE -> AppStateAction.class;
            case BATTERY_STATE -> BatteryStateAction.class;
            case SCREEN_STATE -> ScreenStateAction.class;
            case CAPTURE_STATE -> CaptureStateAction.class;
            case IMAGE_STATE -> ImageStateAction.class;
            case COLOR_STATE -> ColorStateAction.class;
            case DATE_STATE -> DateStateAction.class;
            case TIME_STATE -> TimeStateAction.class;

            case CHECK_IN_APP -> InAppCheckAction.class;
            case CHECK_ON_BATTERY_STATE -> OnBatteryStateAction.class;
            case CHECK_ON_SCREEN_STATE -> OnScreenStateAction.class;
            case CHECK_EXIST_TEXT -> ExistTextAction.class;
            case CHECK_EXIST_NODE -> ExistNodeAction.class;
            case CHECK_EXIST_IMAGE -> ExistImageAction.class;
            case CHECK_EXIST_COLOR -> ExistColorAction.class;
            case CHECK_IMAGE -> ImageContainAction.class;
            case CHECK_COLOR -> ColorEqualAction.class;

            case DELAY -> DelayAction.class;
            case LOG -> LogAction.class;
            case CLICK_POSITION -> ClickPositionAction.class;
            case CLICK_NODE -> ClickNodeAction.class;
            case CLICK_KEY -> ClickKeyAction.class;
            case TOUCH -> TouchAction.class;
            case INPUT -> InputAction.class;
            case SCREEN_SWITCH -> ScreenSwitchAction.class;
            case CAPTURE_SWITCH -> CaptureSwitchAction.class;
            case OPEN_APP -> OpenAppAction.class;
            case OPEN_URI -> OpenUriAction.class;
            case PLAY_RINGTONE -> PlayRingtoneAction.class;
            case COPY -> CopyToClipboardAction.class;
            case RUN_TASK -> RunTaskAction.class;
            case BREAK_TASK -> BreakTaskAction.class;

            case BOOL_OR -> BoolOrAction.class;
            case BOOL_AND -> BoolAndAction.class;
            case BOOL_NOT -> BoolNotAction.class;

            case STRING_FROM_VALUE -> StringFromValueAction.class;
            case STRING_TO_INT -> StringToIntAction.class;
            case STRING_ADD -> StringAddAction.class;
            case STRING_EQUAL -> StringEqualAction.class;
            case STRING_REGEX -> StringRegexAction.class;

            case INT_ADD -> IntAddAction.class;
            case INT_REDUCE -> IntReduceAction.class;
            case INT_MULTI -> IntMultiAction.class;
            case INT_DIV -> IntDivAction.class;
            case INT_MOD -> IntModAction.class;
            case INT_EQUAL -> IntEqualAction.class;
            case INT_LARGE -> IntLargeAction.class;
            case INT_SMALL -> IntSmallAction.class;
            case INT_IN_AREA -> IntInAreaAction.class;
            case INT_RANDOM -> IntRandomAction.class;

            case POS_FROM_INT -> PosFromIntAction.class;
            case POS_TO_INT -> PosToIntAction.class;
            case POS_OFFSET -> PosOffsetAction.class;
            case POS_IN_AREA -> PosInAreaAction.class;
            case POS_TO_AREA -> PosToAreaAction.class;
            case POS_TO_TOUCH -> PosToTouchAction.class;
            default -> Action.class;
        };
    }
}
