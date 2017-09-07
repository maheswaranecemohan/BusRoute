package com.org.busroute.utils;
import android.content.Context;
import android.widget.Toast;

/**
 * This  is ToastUtils
 */
public class ToastUtils {
    public static void showToast(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }
}
