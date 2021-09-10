package com.example.flickrimagesseeker.utils;

import android.app.Activity;
import android.widget.Toast;

import com.example.flickrimagesseeker.App;
import com.example.flickrimagesseeker.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import timber.log.Timber;

public class DialogUtils {

    public static void showTwoButtonsDialog(Activity act, int mssg, OnMessageDialogClosed positiveOnClick, OnMessageDialogClosed negativeOnClick) {
        try {
            act.runOnUiThread(() -> {
                new MaterialAlertDialogBuilder(act, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
                        .setTitle(R.string.app_name)
                        .setCancelable(false)
                        .setMessage(mssg)
                        .setPositiveButton(R.string.alt_accept, (dialog, which) -> {
                            if (positiveOnClick != null)
                                positiveOnClick.onOptionSelected();
                            else
                                dialog.dismiss();
                        })
                        .setNegativeButton(R.string.alt_cancel, (dialog, which) -> {
                            if (negativeOnClick != null)
                                negativeOnClick.onOptionSelected();
                            else
                                dialog.dismiss();
                        })
                        .show();
            });
        } catch (Exception e) {
            Timber.e(e);
            showMessage(R.string.unexpected_error);
        }
    }

    public static void showOneButtonDialog(Activity act, int mssg, OnMessageDialogClosed onClick) {
        try {
            act.runOnUiThread(() -> {
                new MaterialAlertDialogBuilder(act, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
                        .setTitle(R.string.app_name)
                        .setCancelable(false)
                        .setMessage(mssg)
                        .setPositiveButton(R.string.alt_accept, (dialog, which) -> {
                            if (onClick != null)
                                onClick.onOptionSelected();
                            else
                                dialog.dismiss();
                        })
                        .show();
            });
        } catch (Exception e) {
            Timber.e(e);
            showMessage(R.string.unexpected_error);
        }
    }

    public static void showOneButtonDialog(Activity act, String mssg, OnMessageDialogClosed onClick) {
        try {
            act.runOnUiThread(() -> {
                new MaterialAlertDialogBuilder(act, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
                        .setTitle(R.string.app_name)
                        .setCancelable(false)
                        .setMessage(mssg)
                        .setPositiveButton(R.string.alt_accept, (dialog, which) -> {
                            if (onClick != null)
                                onClick.onOptionSelected();
                            else
                                dialog.dismiss();
                        })
                        .show();
            });
        } catch (Exception e) {
            Timber.e(e);
            showMessage(R.string.unexpected_error);
        }
    }

    public static void showMessage(int mssg) {
        Toast.makeText(App.getAppContext(), mssg, Toast.LENGTH_LONG).show();
    }

    public static void showMessage(String mssg) {
        Toast.makeText(App.getAppContext(), mssg, Toast.LENGTH_LONG).show();
    }

    public interface OnMessageDialogClosed {
        void onOptionSelected();
    }
}
