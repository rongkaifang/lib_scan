package com.yunxiao.scan.ui

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yunxiao.scan.R

/**
 * Created by huangshengsen on 2018/12/27.
 */
open class YxTitleBarB @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : YxTitleBar(context, attrs, defStyleAttr) {
    val leftIconView: ImageView
    val titleView: TextView
    val rightTitleView: TextView
    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.YxTitleBarB, defStyleAttr, 0)

    init {
        leftIconView = inflater.inflate(R.layout.yx_titlebar_icon, null) as ImageView
        titleView = inflater.inflate(R.layout.yx_titlebar_title, null) as TextView
        rightTitleView = (inflater.inflate(R.layout.yx_titlebar_b_title_right, null) as TextView)


        leftView.addView(leftIconView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        centerView.addView(titleView)
        rightView.addView(rightTitleView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)

        leftIconView.setOnClickListener {
            (context as Activity).onBackPressed()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        typedArray.getDrawable(R.styleable.YxTitleBarB_titleBarB_leftIcon)?.let { leftIconView.setImageDrawable(it) }
        typedArray.getString(R.styleable.YxTitleBarB_titleBarB_title)?.let { titleView.text = it }
        typedArray.getString(R.styleable.YxTitleBarB_titleBarB_rightTitle)?.let { rightTitleView.text = it }

        typedArray.recycle()

    }


}