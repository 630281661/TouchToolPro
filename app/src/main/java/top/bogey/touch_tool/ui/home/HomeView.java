package top.bogey.touch_tool.ui.home;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import top.bogey.touch_tool.MainAccessibilityService;
import top.bogey.touch_tool.MainApplication;
import top.bogey.touch_tool.R;
import top.bogey.touch_tool.databinding.ViewHomeBinding;
import top.bogey.touch_tool.ui.BaseActivity;
import top.bogey.touch_tool.ui.custom.KeepAliveFloatView;
import top.bogey.touch_tool.utils.AppUtils;
import top.bogey.touch_tool.utils.DisplayUtils;
import top.bogey.touch_tool.utils.SettingSave;
import top.bogey.touch_tool.utils.easy_float.EasyFloat;

public class HomeView extends Fragment {
    private ViewHomeBinding binding;

    @Override
    public void onResume() {
        super.onResume();
        binding.ignoreBatteryBox.setVisibility(AppUtils.isIgnoredBattery(requireContext()) ? View.GONE : View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewHomeBinding.inflate(inflater, container, false);

        binding.serviceButton.setOnClickListener(v -> {
            MainAccessibilityService service = MainApplication.getInstance().getService();
            if (service == null || !service.isServiceConnected()) {
                AppUtils.showDialog(getContext(), getString(R.string.accessibility_service_on_tips, getString(R.string.app_name)), result -> {
                    if (result) {
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        startActivity(intent);
                    }
                });
            } else {
                if (service.isServiceEnabled()) {
                    service.setServiceEnabled(false);
                } else {
                    if (SettingSave.getInstance().isServiceEnabledTip()) {
                        service.setServiceEnabled(true);
                    } else {
                        AppUtils.showDialog(getContext(), R.string.service_des, result -> {
                            if (result) {
                                service.setServiceEnabled(true);
                                SettingSave.getInstance().setServiceEnabledTip(true);
                            }
                        });
                    }
                }
            }
        });
        MainAccessibilityService.serviceConnected.observe(getViewLifecycleOwner(), aBoolean -> binding.accessibilityServiceTitle.setText(aBoolean ? R.string.accessibility_service_on : R.string.accessibility_service_off));

        MainAccessibilityService.serviceEnabled.observe(getViewLifecycleOwner(), aBoolean -> {
            if (MainApplication.getInstance().getService() == null) return;
            if (aBoolean) {
                KeepAliveFloatView floatView = new KeepAliveFloatView(requireContext());
                floatView.show();
            } else {
                EasyFloat.dismiss(KeepAliveFloatView.class.getCanonicalName());
            }
            binding.serviceTitle.setText(aBoolean ? R.string.service_on : R.string.service_off);
            setServiceChecked(aBoolean);
        });

        binding.captureServiceButton.setOnClickListener(v -> ((BaseActivity) requireActivity()).launchNotification((code, intent) -> {
            if (code == Activity.RESULT_OK) {
                MainAccessibilityService service = MainApplication.getInstance().getService();
                if (service != null && service.isServiceConnected()) {
                    if (service.isCaptureEnabled()) {
                        service.stopCaptureService();
                    } else {
                        if (SettingSave.getInstance().isCaptureServiceEnabledTip()) {
                            service.startCaptureService(false, null);
                        } else {
                            AppUtils.showDialog(getContext(), R.string.capture_service_des, result -> {
                                if (result) {
                                    service.startCaptureService(false, null);
                                    SettingSave.getInstance().setCaptureServiceEnabledTip(true);
                                }
                            });
                        }
                    }
                } else {
                    Toast.makeText(getContext(), R.string.accessibility_service_off, Toast.LENGTH_SHORT).show();
                }
            }
        }));
        MainAccessibilityService.captureEnabled.observe(getViewLifecycleOwner(), aBoolean -> {
            binding.captureServiceTitle.setText(aBoolean ? R.string.capture_service_on : R.string.capture_service_off);
            setCaptureChecked(aBoolean);
        });

        binding.ignoreBatteryBox.setVisibility(AppUtils.isIgnoredBattery(requireContext()) ? View.GONE : View.VISIBLE);
        binding.ignoreBatteryButton.setOnClickListener(v -> AppUtils.gotoBatterySetting(getContext()));
        binding.autoStartButton.setOnClickListener(v -> AppUtils.gotoAppDetailSetting(getContext()));
        binding.lockBackgroundButton.setOnClickListener(v -> {
            MainAccessibilityService service = MainApplication.getInstance().getService();
            if (service != null && service.isServiceConnected()) {
                service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
            } else {
                Toast.makeText(getContext(), R.string.accessibility_service_off_tips, Toast.LENGTH_SHORT).show();
            }
        });
        binding.tutorialButton.setOnClickListener(v -> AppUtils.gotoUrl(getContext(), "https://docs.qq.com/doc/p/24efb1da5ef37c58c3687606bd8c169fe73c52d0"));

        return binding.getRoot();
    }

    private void setServiceChecked(boolean checked) {
        if (checked) {
            binding.serviceButton.setCardBackgroundColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0));
            binding.serviceIcon.setImageTintList(ColorStateList.valueOf(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorOnPrimary, 0)));
            binding.serviceTitle.setTextColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorOnPrimary, 0));
            binding.accessibilityServiceTitle.setTextColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorOnPrimary, 0));
        } else {
            binding.serviceButton.setCardBackgroundColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorSurfaceVariant, 0));
            binding.serviceIcon.setImageTintList(ColorStateList.valueOf(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0)));
            binding.serviceTitle.setTextColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0));
            binding.accessibilityServiceTitle.setTextColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0));
        }
    }

    private void setCaptureChecked(boolean checked) {
        if (checked) {
            binding.captureServiceButton.setCardBackgroundColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0));
            binding.captureServiceIcon.setImageTintList(ColorStateList.valueOf(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorOnPrimary, 0)));
            binding.captureServiceTitle.setTextColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorOnPrimary, 0));
        } else {
            binding.captureServiceButton.setCardBackgroundColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorSurfaceVariant, 0));
            binding.captureServiceIcon.setImageTintList(ColorStateList.valueOf(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0)));
            binding.captureServiceTitle.setTextColor(DisplayUtils.getAttrColor(requireContext(), com.google.android.material.R.attr.colorPrimary, 0));
        }
    }
}