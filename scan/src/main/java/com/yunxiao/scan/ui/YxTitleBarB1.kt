package com.yunxiao.scan.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.yunxiao.scan.R

/**
 * Created by huangshengsen on 2018/12/27.
 */
class YxTitleBarB1 @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : YxTitleBarB(context, attrs, defStyleAttr) {

    init {
        leftIconView.setImageResource(R.drawable.selector_back)
        setBackgroundColor(ContextCompat.getColor(context, R.color.c01))
        bottomView.visibility = View.VISIBLE
    }


}