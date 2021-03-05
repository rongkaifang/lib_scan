package com.yunxiao.scan.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunxiao.scan.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by liwei on 15/6/18.
 */

/**
 *  *
 *  * Create custom Dialog windows for your application
 *  * Custom dialogs rely on custom layouts wich allow you to
 *  * create and use your own look & feel.
 *  *
 *  * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 *  *
 *  * <a href="http://my.oschina.net/arthor" target="_blank" rel="nofollow">@author</a> antoine vianey
 *  *
 *  
 */
public class YxAlertDialog extends Dialog {

    public static final int BUTTON_POSITIVE = 0;
    public static final int BUTTON_NEGATIVE = 1;
    private static final int TRANSLATE_DURATION = 300;
    private static final int ALPHA_DURATION = 300;
    public LinearLayout bottomContainsLl;
    private Button positiveBtn;
    private Button negativeBtn;
    private View mBg;
    private View mContentLy;

    private Context context;

    public YxAlertDialog(Context context) {
        super(context, android.R.style.Theme_Light_NoTitleBar);
        this.context = context;
        Drawable drawable = new ColorDrawable();
        drawable.setAlpha(0);// 设置透明背景
        getWindow().setBackgroundDrawable(drawable);
    }

    /**
     *  * Helper class for creating a custom dialog
     *  
     */

    public static class Builder {
        private Context context;
        private String title;
        private CharSequence message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private View titleLy;
        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;
        private OnCancelListener mOnCancelListener;
        private OnDismissListener mOnDismissListener;
        private boolean isCancelable;
        private boolean isPositiveBtnAutoCancelable;
        private boolean isNegativeBtnAutoCancelable;
        private boolean isCanceledOnTouchOutside;
        private int textGravity;

        public Builder(Context context) {
            this.context = context;

            isCancelable = true;
            isPositiveBtnAutoCancelable = true;
            isNegativeBtnAutoCancelable = true;
            isCanceledOnTouchOutside = false;
            textGravity = Gravity.CENTER;
        }

        /**
         *          * Set the Dialog message from String
         *          * @param title
         *          * @return
         *          
         *
         * @param message
         */

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        /**
         *          * Set the Dialog message from resource
         *          * @param title
         *          * @return
         *          
         */

        public Builder setMessage(@StringRes int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         *          * Set the Dialog title from resource
         *          * @param title
         *          * @return
         *          
         */

        public Builder setTitle(@StringRes int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         *          * Set the Dialog title from String
         *          * @param title
         *          * @return
         *          
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         *          * Set a custom content view for the Dialog.
         *          * If a message is set, the contentView is not
         *          * added to the Dialog...
         *          * @param v
         *          * @return
         *          
         */

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         *          * Set the positive button resource and it's listener
         *          * @param positiveButtonText
         *          * @param listener
         *          * @return
         *          
         */

        public Builder setPositiveButton(@StringRes int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         *          * Set the positive button text and it's listener
         *          * @param positiveButtonText
         *          * @param listener
         *          * @return
         *          
         */

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         *          * Set the negative button resource and it's listener
         *          * @param negativeButtonText
         *          * @param listener
         *          * @return
         *          
         */

        public Builder setNegativeButton(@StringRes int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         *          * Set the negative button text and it's listener
         *          * @param negativeButtonText
         *          * @param listener
         *          * @return
         *          
         */

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * 设置点击半透明背景是否可以dismiss
         *
         * @param isCanceledOnTouchOutside
         * @return
         */
        public Builder setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
            this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
            return this;
        }

        /**
         * 设置dialog取消监听器
         *
         * @param onCancelListener
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * 设置dialog小时监听器
         *
         * @param onDismissListener
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setTextGravity(int gravity) {
            textGravity = gravity;
            return this;
        }

        /**
         * 设置dialog是否可以取消
         *
         * @param isCancelable
         */
        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        /**
         * 设置PositiveButton点击是否自动消失。
         *
         * @param isPositiveBtnAutoCancelable false，点击不消失，需要手动调dialog.dismiss(),默认为true，
         */
        public Builder setPositiveButtonAutoCancelable(boolean isPositiveBtnAutoCancelable) {
            this.isPositiveBtnAutoCancelable = isPositiveBtnAutoCancelable;
            return this;
        }

        /**
         * 设置NegativeButton点击是否自动消失。
         *
         * @param isNegativeBtnAutoCancelable false，点击不消失，需要手动调dialog.dismiss(),默认为true，
         */
        public Builder setNegativeButtonAutoCancelable(boolean isNegativeBtnAutoCancelable) {
            this.isNegativeBtnAutoCancelable = isNegativeBtnAutoCancelable;
            return this;
        }

        /**
         * * Create the custom dialog
         *          
         */

        public YxAlertDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final YxAlertDialog dialog = new YxAlertDialog(context);
            dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
            dialog.setCancelable(isCancelable);
            View layout = inflater.inflate(R.layout.alert_dialog, null);
            dialog.mBg = layout.findViewById(R.id.ll_dialog_bg);
            dialog.mContentLy = layout.findViewById(R.id.ll_dialog_content);
            titleLy = layout.findViewById(R.id.ll_dialog_title);
            dialog.bottomContainsLl = (LinearLayout) layout.findViewById(R.id.bottomContainsLl);
            dialog.positiveBtn = (Button) layout.findViewById(R.id.positiveButton);
            dialog.negativeBtn = (Button) layout.findViewById(R.id.negativeButton);
            if (isCanceledOnTouchOutside) {
                dialog.mBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
            dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            if (!TextUtils.isEmpty(title)) {
                titleLy.setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.title)).setText(title);
            } else {
                titleLy.setVisibility(View.GONE);
            }
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);

                dialog.positiveBtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (positiveButtonClickListener != null) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                        if (isPositiveBtnAutoCancelable) {
                            dialog.dismiss();
                        }
                    }

                });
            } else {
                // if no confirm button just set the visibility to GONE
                dialog.positiveBtn.setVisibility(View.GONE);
            }
            if (mOnCancelListener != null) {
                dialog.setOnCancelListener(mOnCancelListener);
            }
            if (mOnDismissListener != null) {
                dialog.setOnDismissListener(mOnDismissListener);
            }
            // set the cancel button
            if (negativeButtonText != null) {

                ((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);


                dialog.negativeBtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (negativeButtonClickListener != null) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                        if (isNegativeBtnAutoCancelable) {
                            dialog.dismiss();
                        }

                    }

                });
            } else {
                // if no confirm button just set the visibility to GONE
                dialog.negativeBtn.setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setGravity(textGravity);
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();

                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            //TODO 待优化，确认修改后可以把获取屏幕宽高的代码放到构造函数中
            //处理横竖屏dialog对应的宽
            DisplayMetrics dm = new DisplayMetrics();
            dialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            LayoutParams layoutParams = dialog.mContentLy.getLayoutParams();

            Configuration configuration = dialog.getContext().getResources().getConfiguration();
            //横屏状态，dialog宽为屏幕宽的一半，竖屏则为屏幕宽
            layoutParams.width = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ? dm.widthPixels / 2 : dm.widthPixels;
            dialog.mContentLy.setLayoutParams(layoutParams);

            return dialog;
        }

    }

    @Override
    public void show() {
        super.show();
        mBg.startAnimation(createAlphaInAnimation());
        mContentLy.startAnimation(createAlphaInAnimation());
    }

    @Override
    public void dismiss() {
        mBg.startAnimation(createAlphaOutAnimation());
        mContentLy.startAnimation(createAlphaOutAnimation());
        mBg.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (context instanceof Activity) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!((Activity) context).isDestroyed()) {
                            YxAlertDialog.super.dismiss();
                        }
                    } else {
                        if (!((Activity) context).isFinishing()) {
                            YxAlertDialog.super.dismiss();
                        }
                    }
                } else {
                    YxAlertDialog.super.dismiss();
                }
            }
        }, TRANSLATE_DURATION);
    }

    private Animation createAlphaInAnimation() {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(ALPHA_DURATION);
        return an;
    }

    private Animation createAlphaOutAnimation() {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(ALPHA_DURATION);
        an.setFillAfter(true);
        return an;
    }

    public Button getButton(@WhichButton int whichButton) {

        return whichButton == BUTTON_POSITIVE ? positiveBtn : negativeBtn;
    }

    @IntDef({BUTTON_POSITIVE, BUTTON_NEGATIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WhichButton {
    }


}