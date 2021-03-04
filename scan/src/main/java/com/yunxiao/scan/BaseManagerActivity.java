package com.yunxiao.scan;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

import com.yunxiao.base.YxBaseActivity;

import java.util.LinkedList;

/**
 * Created by wangguixiang on 17/12/20.
 */

public class BaseManagerActivity extends YxBaseActivity {

    /**
     * 下一次Activity启动时，新Activity的进入动画
     */
    private static int sNextEnterAnimWhenStarting;
    /**
     * 下一次Activity启动时，旧Activity的退出动画
     */
    private static int sNextExitAnimWhenStarting;
    /**
     * 下一次Activity结束时，新Activity的进入动画
     */
    private static int sNextEnterAnimWhenFinishing;
    /**
     * 下一次Activity结束时，旧Activity的退出动画
     */
    private static int sNextExitAnimWhenFinishing;
    /**
     * Invalid animation
     */
    protected static final int INVALID_ANIM = 0;
    /**
     * 启动时，新Activity的进入动画
     */
    private int mEnterAnimWhenStarting = INVALID_ANIM;
    /**
     * 启动时，旧Activity的退出动画
     */
    private int mExitAnimWhenStarting = INVALID_ANIM;
    /**
     * 结束时，新Activity的进入动画
     */
    private int mEnterAnimWhenFinishing = INVALID_ANIM;
    /**
     * 结束时，旧Activity的退出动画
     */
    private int mExitAnimWhenFinishing = INVALID_ANIM;

    /**
     * 窗口栈。
     */
    private static LinkedList<Activity> sActivityStack = new LinkedList<Activity>();

    /**
     * 当前活动的Activity数，用来判断该应用是否在前台运行。
     */
    private static int sLiveActivityNum = 0;

    /**
     * 是否刚刚回到前台。
     */
    private static boolean sIsFirstIn = true;

    /**
     * 当没有Activity时，是否杀死进程。
     */
    private static boolean sEmptyKillApp = true;

    /**
     * 抽取基类周期函数实现, 以方便无法集成baseactivity的activity调用base方法
     *
     * @param activityProxy Activity 代理，可能不是真正的Activity对象，使用时慎重！！
     */

    public static void onBaseCreate(Activity activityProxy) {
        if (activityProxy == null) {
            return;
        }
        addToTask(activityProxy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBaseCreate(this);
        // 设置本次Activity切换动画
        if (sNextEnterAnimWhenStarting != INVALID_ANIM || sNextExitAnimWhenStarting != INVALID_ANIM) {
            mEnterAnimWhenStarting = sNextEnterAnimWhenStarting;
            mExitAnimWhenStarting = sNextExitAnimWhenStarting;
        }

        if (sNextEnterAnimWhenFinishing != INVALID_ANIM || sNextExitAnimWhenFinishing != INVALID_ANIM) {
            mEnterAnimWhenFinishing = sNextEnterAnimWhenFinishing;
            mExitAnimWhenFinishing = sNextExitAnimWhenFinishing;
        }

        setNextPendingTransition(INVALID_ANIM, INVALID_ANIM, INVALID_ANIM, INVALID_ANIM);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 抽取基类周期函数实现, 以方便无法集成baseactivity的activity调用base方法
     *
     * @param activityProxy Activity 代理，可能不是真正的Activity对象，使用时慎重！！
     */
    public static void onBaseResume(Activity activityProxy) {
        if (activityProxy == null) {
            return;
        }
        addLiveActivityNum();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onBaseResume(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置本次Activity切换动画
        if (sNextEnterAnimWhenStarting != 0 || sNextExitAnimWhenStarting != 0) {
            mEnterAnimWhenStarting = sNextEnterAnimWhenStarting;
            mExitAnimWhenStarting = sNextExitAnimWhenStarting;
        }

        if (sNextEnterAnimWhenFinishing != 0 || sNextExitAnimWhenFinishing != 0) {
            mEnterAnimWhenFinishing = sNextEnterAnimWhenFinishing;
            mExitAnimWhenFinishing = sNextExitAnimWhenFinishing;
        }

        setNextPendingTransition(0, 0, 0, 0);

        // 添加Activity启动的动画
        if (mEnterAnimWhenStarting != 0 || mExitAnimWhenStarting != 0) {
            overridePendingTransition(mEnterAnimWhenStarting, mExitAnimWhenStarting);
            mEnterAnimWhenStarting = 0;
            mExitAnimWhenStarting = 0;
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            supportFinishAfterTransition();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        // 添加Activity启动的动画
        if (mEnterAnimWhenStarting != INVALID_ANIM || mExitAnimWhenStarting != INVALID_ANIM) {
            overridePendingTransition(mEnterAnimWhenStarting, mExitAnimWhenStarting);
            mEnterAnimWhenStarting = INVALID_ANIM;
            mExitAnimWhenStarting = INVALID_ANIM;
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 抽取基类周期函数实现, 以方便无法集成baseactivity的activity调用base方法
     *
     * @param activityProxy Activity 代理，可能不是真正的Activity对象，使用时慎重！！
     */
    public static void onBasePause(Activity activityProxy) {
        if (activityProxy == null) {
            return;
        }
        decLiveActivityNum();
    }

    @Override
    protected void onPause() {
        onBasePause(this);
        super.onPause();
    }

    /**
     * 抽取基类周期函数实现, 以方便无法集成baseactivity的activity调用base方法
     *
     * @param activityProxy Activity 代理，可能不是真正的Activity对象，使用时慎重！！
     */
    public static void onBaseStop(Activity activityProxy) {
        if (activityProxy == null) {
        }
    }

    @Override
    protected void onStop() {
        onBaseStop(this);
        super.onStop();
    }

    /**
     * 抽取基类周期函数实现, 以方便无法集成baseactivity的activity调用base方法
     *
     * @param activityProxy Activity 代理，可能不是真正的Activity对象，使用时慎重！！
     */
    public static void onBaseDestroy(Activity activityProxy) {
        if (activityProxy == null) {
            return;
        }
        removeFromTask(activityProxy);
    }

    @Override
    protected void onDestroy() {
        onBaseDestroy(this);
        CommonUtils.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }

    /**
     * 获取栈顶Activity
     *
     * @return Activity
     */
    public static Activity getTopActivity() {
        return sActivityStack.getLast();
    }

    /**
     * 将Activity加入栈管理队列中。
     *
     * @param activity Activity。
     */
    public static synchronized void addToTask(Activity activity) {
        // 移到顶端。
        sActivityStack.remove(activity);
        sActivityStack.add(activity);
    }

    /**
     * 从栈管理队列中移除该Activity。
     *
     * @param activity Activity。
     */
    public static synchronized void removeFromTask(Activity activity) {
        sActivityStack.remove(activity);
    }

    /**
     * 清除栈队列中的所有Activity。
     */
    public static synchronized void clearTask() {
        if (!sActivityStack.isEmpty()) {
            sEmptyKillApp = false;
        }

        for (Activity activity : sActivityStack) {
            activity.finish();
        }
    }

    /**
     * 清除栈队列中除exceptActivity以外的所有Activity。与FLAG_ACTIVITY_CLEAR_TASK功能类似。
     *
     * @param exceptActivity exceptActivity。
     */
    public static synchronized void clearTaskExcept(Activity exceptActivity) {
        for (Activity activity : sActivityStack) {

            if (activity != exceptActivity) {
                activity.finish();
            }
        }
    }

    /**
     * 清除栈队列中除exceptClasses 以外的所有Activity。与FLAG_ACTIVITY_CLEAR_TASK功能类似。
     *
     * @param exceptClasses exceptClasses
     */
    public static synchronized void clearTaskExcept(Class[] exceptClasses) {
        for (Activity activity : sActivityStack) {
            boolean exist = false;
            for (Class exceptClass : exceptClasses) {
                if (activity.getClass().isAssignableFrom(exceptClass)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                activity.finish();
            }
            exist = false;
        }
    }

    /**
     * 退出应用。
     *
     * @param context Context.
     */
    public static void quitApp(Context context) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 判断应用是否在前台。
     *
     * @return 如果在前台，返回true.
     */
    public static boolean isAppInForeground() {
        return sLiveActivityNum > 0;
    }

    /**
     * 判断应用是否回到前台。
     *
     * @return
     */
    public static boolean isBackForeground() {
        return sLiveActivityNum == 1;
    }

    /**
     * 判断应用程序是否刚刚回到前台。
     *
     * @return 如果是，返回true。
     */
    public static boolean isFirstIn() {
        return sIsFirstIn;
    }

    /**
     * 设置状态，标识应用程序是否刚刚回到前台。
     *
     * @param isFirstIn 应用程序是否刚刚回到前台。
     */
    public static void setFirstIn(boolean isFirstIn) {
        sIsFirstIn = isFirstIn;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // FIX 一个长按菜单的bug，在特定机型出现
        if (keyCode == KeyEvent.KEYCODE_MENU && event.isLongPress()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置下一次Activity的切换动画，enter与exit同时为0时用默认
     *
     * @param enterAnimWhenStarting  下一次Activity启动时，新Activity的进入动画
     * @param exitAnimWhenStarting   下一次Activity启动时，旧Activity的退出动画
     * @param enterAnimWhenFinishing 下一次Activity结束时，新Activity的进入动画
     * @param exitAnimWhenFinishing  下一次Activity结束时，旧Activity的退出动画
     * @see Activity#overridePendingTransition(int, int)
     */
    public static void setNextPendingTransition(int enterAnimWhenStarting, int exitAnimWhenStarting,
                                                int enterAnimWhenFinishing, int exitAnimWhenFinishing) {
        BaseManagerActivity.sNextEnterAnimWhenStarting = enterAnimWhenStarting;
        BaseManagerActivity.sNextExitAnimWhenStarting = exitAnimWhenStarting;
        BaseManagerActivity.sNextEnterAnimWhenFinishing = enterAnimWhenFinishing;
        BaseManagerActivity.sNextExitAnimWhenFinishing = exitAnimWhenFinishing;
    }

    /**
     * 设置本次的切换动画，enter与exit同时为0时用默认
     *
     * @param enterAnimWhenStarting  启动时，新Activity的进入动画
     * @param exitAnimWhenStarting   启动时，旧Activity的退出动画
     * @param enterAnimWhenFinishing 结束时，新Activity的进入动画
     * @param exitAnimWhenFinishing  结束时，旧Activity的退出动画
     * @see Activity#overridePendingTransition(int, int)
     */
    protected void setPendingTransition(int enterAnimWhenStarting, int exitAnimWhenStarting,
                                        int enterAnimWhenFinishing, int exitAnimWhenFinishing) {
        mEnterAnimWhenStarting = enterAnimWhenStarting;
        mExitAnimWhenStarting = exitAnimWhenStarting;
        mEnterAnimWhenFinishing = enterAnimWhenFinishing;
        mExitAnimWhenFinishing = exitAnimWhenFinishing;
    }

    @Override
    public void finish() {
        super.finish();
        // 添加Activity退出的动画
        if (mEnterAnimWhenFinishing != INVALID_ANIM || mExitAnimWhenFinishing != INVALID_ANIM) {
            overridePendingTransition(mEnterAnimWhenFinishing, mExitAnimWhenFinishing);
            mEnterAnimWhenFinishing = INVALID_ANIM;
            mExitAnimWhenFinishing = INVALID_ANIM;
        }
    }

    /**
     * 不能直接继承baseactivity的activity，在onresume时调用
     */
    public static void addLiveActivityNum() {
        sLiveActivityNum++;
    }

    /**
     * 不能直接继承baseactivity的activity，在onpause时调用
     */
    public static void decLiveActivityNum() {
        sLiveActivityNum--;
    }

    /**
     * 活跃的activity数目
     *
     * @return 活跃的activity数目
     */
    public static int getLiveActivityNum() {
        return sLiveActivityNum;
    }

    /**
     * 是初次进入
     *
     * @return true 是 false不是
     */
    public static boolean isActivityFirstIn() {
        return sIsFirstIn;
    }

}
