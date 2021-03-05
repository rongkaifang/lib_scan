package com.yunxiao.scan.ui

import android.content.Context
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.yunxiao.scan.R

/**
 * Created by huangshengsen on 2018/12/27.
 */
open class YxTitleBar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    val view by lazy {
        findViewById<ViewGroup>(R.id.rootView)
    }
    val leftView by lazy {
        findViewById<ViewGroup>(R.id.layout_left)
    }
    val centerView by lazy {
        findViewById<ViewGroup>(R.id.layout_center)
    }
    val rightView by lazy {
        findViewById<ViewGroup>(R.id.layout_right)
    }
    val bottomView by lazy {
        findViewById<View>(R.id.view_bottom)
    }
    val inflater = LayoutInflater.from(context)

    init {
        inflater.inflate(R.layout.yx_titlebar, this, true)
    }

    override fun setBackgroundColor(@ColorInt color: Int) {
        view.setBackgroundColor(color)
    }

}
