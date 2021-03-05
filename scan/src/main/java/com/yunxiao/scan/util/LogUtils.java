package com.yunxiao.scan.util;

import android.util.Log;

import com.yunxiao.scan.BuildConfig;


public class LogUtils {

    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final int LEVEL = BuildConfig.DEBUG ? Log.VERBOSE : Log.WARN;

    public static boolean isDebug() {
        return LEVEL <= Log.DEBUG;
    }

    static String className;
    static String methodName;
    static int lineNumber;

    public static void v(String msg) {
        if (LEVEL <= Log.VERBOSE) {
            getFileMethodLine(new Throwable().getStackTrace());

            Log.v(className, getPrefix() + msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (LEVEL <= Log.VERBOSE) {
            getFileMethodLine(tr.getStackTrace());
            Log.v(className, getPrefix() + msg, tr);
        }
    }

    public static void v(String format, Object... args) {
        if (LEVEL <= Log.VERBOSE) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.v(className, getPrefix() + logFormat(format, args));
        }
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= Log.VERBOSE) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.v(tag, getPrefix() + msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (LEVEL <= Log.VERBOSE) {
            getFileMethodLine(tr.getStackTrace());
            Log.v(tag, getPrefix() + msg, tr);
        }
    }

    public static void v(String tag, String format, Object... args) {
        if (LEVEL <= Log.VERBOSE) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.v(tag, getPrefix() + logFormat(format, args));
        }
    }

    public static void d(String msg) {
        if (LEVEL <= Log.DEBUG) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.d(className, getPrefix() + msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (LEVEL <= Log.DEBUG) {
            getFileMethodLine(tr.getStackTrace());
            Log.d(className, getPrefix() + msg, tr);
        }
    }

    public static void d(String format, Object... args) {
        if (LEVEL <= Log.DEBUG) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.d(className, getPrefix() + logFormat(format, args));
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= Log.DEBUG) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.d(tag, getPrefix() + msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (LEVEL <= Log.DEBUG) {
            getFileMethodLine(tr.getStackTrace());
            Log.d(tag, getPrefix() + msg, tr);
        }
    }

    public static void d(String tag, String format, Object... args) {
        if (LEVEL <= Log.DEBUG) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.d(tag, getPrefix() + logFormat(format, args));
        }
    }

    public static void i(String msg) {
        if (LEVEL <= Log.INFO) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.i(className, getPrefix() + msg);
        }
    }

    public static void i(String msg, Throwable tr) {
        if (LEVEL <= Log.INFO) {
            getFileMethodLine(tr.getStackTrace());
            Log.i(className, getPrefix() + msg, tr);
        }
    }

    public static void i(String format, Object... args) {
        if (LEVEL <= Log.INFO) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.i(className, getPrefix() + logFormat(format, args));
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= Log.INFO) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.i(tag, getPrefix() + msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (LEVEL <= Log.INFO) {
            getFileMethodLine(tr.getStackTrace());
            Log.i(tag, getPrefix() + msg, tr);
        }
    }

    public static void i(String tag, String format, Object... args) {
        if (LEVEL <= Log.INFO) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.i(tag, getPrefix() + logFormat(format, args));
        }
    }

    public static void w(String msg) {
        if (LEVEL <= Log.WARN) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.w(className, getPrefix() + msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (LEVEL <= Log.WARN) {
            getFileMethodLine(tr.getStackTrace());
            Log.w(className, getPrefix() + msg, tr);
        }
    }

    public static void w(String format, Object... args) {
        if (LEVEL <= Log.WARN) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.w(className, getPrefix() + logFormat(format, args));
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= Log.WARN) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.w(tag, getPrefix() + msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (LEVEL <= Log.WARN) {
            getFileMethodLine(tr.getStackTrace());
            Log.w(tag, getPrefix() + msg, tr);
        }
    }

    public static void w(String tag, String format, Object... args) {
        if (LEVEL <= Log.WARN) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.w(tag, getPrefix() + logFormat(format, args));
        }
    }

    public static void e(String msg) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.e(className, getPrefix() + msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(tr.getStackTrace());
            Log.e(className, getPrefix() + msg, tr);
        }
    }

    public static void e(String format, Object... args) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.e(className, getPrefix() + logFormat(format, args));
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.e(tag, getPrefix() + msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(tr.getStackTrace());
            Log.e(tag, getPrefix() + msg, tr);
        }
    }

    public static void e(String tag, String format, Object... args) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.e(tag, getPrefix() + logFormat(format, args));
        }
    }

    public static void wtf(String msg) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.wtf(className, getPrefix() + msg);
        }
    }

    public static void wtf(String msg, Throwable tr) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(tr.getStackTrace());
            Log.wtf(className, getPrefix() + msg, tr);
        }
    }

    public static void wtf(String format, Object... args) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.wtf(className, getPrefix() + logFormat(format, args));
        }
    }

    public static void wtf(String tag, String msg) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.wtf(tag, getPrefix() + msg);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(tr.getStackTrace());
            Log.wtf(tag, getPrefix() + msg, tr);
        }
    }

    public static void wtf(String tag, String format, Object... args) {
        if (LEVEL <= Log.ERROR) {
            getFileMethodLine(new Throwable().getStackTrace());
            Log.wtf(tag, getPrefix() + logFormat(format, args));
        }
    }

    private static String logFormat(String format, Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String[]) {
                args[i] = prettyArray((String[]) args[i]);
            }
        }
        String s = String.format(format, args);
        s = "[" + Thread.currentThread().getId() + "] " + s;
        return s;
    }

    private static String prettyArray(String[] array) {
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        int len = array.length - 1;
        for (int i = 0; i < len; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[len]);
        sb.append("]");

        return sb.toString();
    }

    private static String getPrefix() {

        return "[" + methodName + "|" + lineNumber + "] ";
    }

    private static void getFileMethodLine(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

}
