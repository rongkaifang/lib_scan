package com.yunxiao.scan.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import me.drakeet.support.toast.ToastCompat;


/**
 * Created by huangshengsen on 17/2/7.
 */
public class ToastUtils {
    private static void showToast(Context context, String msg, int gravity, int time) {
        ToastCompat toast = ToastCompat.makeText(context, msg, time);
        if (gravity != -1) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }

    private static void showToast(Context context, @StringRes int msg, int gravity, int time) {
        Toast toast = ToastCompat.makeText(context, msg, time);
        if (gravity != -1) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }

    public static void showShortToast(Context context, String msg) {
        showToast(context, msg, -1, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context, @StringRes int msg) {
        showToast(context, msg, -1, Toast.LENGTH_SHORT);
    }


    public static void showShortToastInCenter(Context context, String msg) {
        showToast(context, msg, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    public static void showShortToastInCenter(Context context, @StringRes int msg) {
        showToast(context, msg, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, String msg) {
        showToast(context, msg, -1, Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context context, @StringRes int msg) {
        showToast(context, msg, -1, Toast.LENGTH_LONG);
    }

    public static void showLongToastInCenter(Context context, String msg) {
        showToast(context, msg, Gravity.CENTER, Toast.LENGTH_LONG);
    }

    public static void showLongToastInCenter(Context context, @StringRes int msg) {
        showToast(context, msg, Gravity.CENTER, Toast.LENGTH_LONG);
    }
}
