package com.yunxiao.scan.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 通用的工具方法类
 */
public final class CommonUtils {

    /**
     * 私有的构造方法
     */
    private CommonUtils() {

    }

    /**
     * 把float转换为显示的String，如果小数点后面是0，则显示整数部分；
     * 默认保留小数点后2位
     *
     * @param xx float数字
     * @return 显示的String
     */
    public static String getDisplayString(float xx) {
        return getDisplayString(xx, 2);
    }


    /**
     * 把float转换为显示的String，如果小数点后面是0，则显示整数部分；
     *
     * @param xx float数字
     * @return 显示的String
     */
    public static String getDisplayString(float xx, int decimalCount) {
        float newXx = getFloatText(xx, decimalCount);
        int yy = (int) newXx;
        if (0 == (newXx - (float) yy)) {
            return String.valueOf(yy);
        } else {
            return String.valueOf(newXx);
        }
    }

    /**
     * 把float精确到小数点后几位，
     *
     * @param data         float数字
     * @param decimalCount 保留几位小数
     */
    private static float getFloatText(float data, int decimalCount) {
        try {
            BigDecimal b = BigDecimal.valueOf(data);
            //BigDecimal.ROUND_HALF_UP：表示四舍五入
            return b.setScale(decimalCount, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (NumberFormatException e) {
            return 0f;
        }
    }

    /**
     * 保留两位小数，0也不省略
     *
     * @param data
     * @return
     */
    public static String getFloatTextCustom(float data) {
        try {
            BigDecimal obj = new BigDecimal(data);
            obj.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            DecimalFormat df = new DecimalFormat("#.00");
            if (obj.compareTo(BigDecimal.ZERO) == 0) {
                return "0.00";
            } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
                return "0" + df.format(obj);
            } else {
                return df.format(obj);
            }
        } catch (NumberFormatException e) {
            return "0.00";
        }
    }

    /**
     * 将float转换成String，并保留小数点后一位小数，如果是整数，则以".0"的方式显示
     *
     * @param data
     * @return
     */
    public static String getFloatText(float data) {
        return String.valueOf(getFloatText(data, 1));
    }

    /**
     * 判断apk是否安装
     *
     * @param context
     * @param packageName：包名
     * @return
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 根据包名启动app
     *
     * @param mContext
     * @param appPackageName
     */
    public static Intent startAPP(Context mContext, String appPackageName) {
        Intent intent;
        try {
            intent = mContext.getPackageManager().getLaunchIntentForPackage(appPackageName);
        } catch (Exception e) {
            intent = null;
        }
        return intent;
    }

    /**
     * 将字符串数组转换成字符串，并带分隔符","
     *
     * @param array
     * @return
     */
    public static String array2String(String[] array) {
        if (array != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    sb.append(array[i]);
                } else {
                    sb.append(array[i] + ",");
                }
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 去掉非字母的字符，然后剩余字母大写，并升序组成字符串返回
     *
     * @param src
     * @return
     */
    public static String getSortedUpperString(String src) {
        if (TextUtils.isEmpty(src)) {
            return src;
        }

        StringBuffer buffer = new StringBuffer();
        char[] chars = src.toCharArray();
        for (char c : chars) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                buffer.append(c);
            }
        }

        char[] list = buffer.toString().toCharArray();
        Arrays.sort(list);
        return String.valueOf(list).toUpperCase();
    }

    /**
     * 将string转换成int并捕获异常
     *
     * @param s
     * @return
     */
    public static int parseString2Int(String s, int defaultValue) {
        if (TextUtils.isEmpty(s)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            LogUtils.d(CommonUtils.class.getSimpleName(), e);
        }
        return defaultValue;
    }

    /**
     * 将string转换成float并捕获异常
     *
     * @param s
     * @return
     */
    public static float parseString2Float(String s, float defaultValue) {
        if (TextUtils.isEmpty(s)) {
            return defaultValue;
        }
        try {
            return Float.valueOf(s);
        } catch (NumberFormatException e) {
            LogUtils.d(CommonUtils.class.getSimpleName(), e);
        }
        return defaultValue;
    }

    /**
     * 是否是主进程
     *
     * @param application application
     * @return
     */
    public static boolean isMainProcess(Application application) {
        int pid = android.os.Process.myPid();
        String processName = getProcessName(application, pid);
        //判断进程名，保证只有主进程运行
        if (processName == null) {
            return false;
        } else {
            return !TextUtils.isEmpty(processName) && processName.equals(application.getPackageName());
        }
    }

    /**
     * 获取进程名称
     *
     * @param cxt application
     * @param pid process id
     * @return
     */
    public static String getProcessName(Application cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 解决InputMethodManager内存泄漏问题
     *
     * @param destContext context
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object objGet = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                objGet = f.get(imm);
                if (objGet != null && objGet instanceof View) {
                    View vGet = (View) objGet;
                    if (vGet.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                LogUtils.d(CommonUtils.class.getSimpleName(), t);
            }
        }
        fixLeakCanary696(destContext);
    }

    static void fixLeakCanary696(Context context) {
        if (!isEmui()) {
            Log.w("fix", "not emui");
            return;
        }
        try {
            Class clazz = Class.forName("android.gestureboost.GestureBoostManager");
            Log.w("fix", "clazz " + clazz);

            Field gestureBoostManager = clazz.getDeclaredField("sGestureBoostManager");
            gestureBoostManager.setAccessible(true);
            Field mContext = clazz.getDeclaredField("mContext");
            mContext.setAccessible(true);

            Object sGestureBoostManager = gestureBoostManager.get(null);
            if (sGestureBoostManager != null) {
                mContext.set(sGestureBoostManager, context);
            }
        } catch (Exception ignored) {
        }
    }

    static boolean isEmui() {
        return !TextUtils.isEmpty(getSystemProperty("ro.build.version.emui"));
    }

    static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
            }
        }
        return line;
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    /**
     * 验证手机号是否合法，第一位必须是1，第二位非0、1、2，的11位数字
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            return false;
        }
        String regexMobile;
        // regex_mobile = "^1(3[0-9]|4[57]|5[^4,\\D]|8[0-9]|7[06-8])\\d{8}$";
        regexMobile = "^1([3-9])\\d{9}$";

        //  移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        //  联通：130、131、132、152、155、156、185、186
        //  电信：133、153、180、189、（1349卫通）
        //String regex_mobile = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        //  电信号段:133/153/180/181/189/177
        //  联通号段:130/131/132/155/156/185/186/145/176
        //  移动号段:134/135/136/137/138/139/150/151/152/157/158/159/182/183/184/187/188/147/178
        //  虚拟运营商:170
        return Pattern.matches(regexMobile, phoneNum);
    }

    /**
     * 校验密码是否合法，长度必须在6-20位之间的字母数字或者下划线
     *
     * @param pwd 密码
     * @return 是否合法
     */
    public static boolean isPassword(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }

        String regexPwd = "^[a-zA-Z0-9_]{6,20}$";
        return Pattern.matches(regexPwd, pwd);
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.d(CommonUtils.class.getSimpleName(), e);
        }
        return "";
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.d(CommonUtils.class.getSimpleName(), e);
        }
        return 0;
    }

    private static String sDeviceId;

    /**
     * 获取设备id
     *
     * @return
     */
    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(sDeviceId)) {
            String androidId = CommonUtils.getAndroidId(context);
            String deviceId = "";
            try {
                deviceId = MD5Utils.toMd5((androidId).getBytes("utf-8"), true);
            } catch (UnsupportedEncodingException e) {
                LogUtils.d(CommonUtils.class.getSimpleName(), e);
            }
            sDeviceId = deviceId;
            return sDeviceId;
        }
        return sDeviceId;
    }

    /**
     * 获取AndroidId
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String id = "";
        id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(id)) {
            id = getIMEI();
        }
        return id;
    }
    /**
     * 获取伪IMEI
     */
    private static String getIMEI() {
        return "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface netIf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netIf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("-?[0-9]*+.?[0-9]*+");
        return pattern.matcher(s).matches();
    }
}
