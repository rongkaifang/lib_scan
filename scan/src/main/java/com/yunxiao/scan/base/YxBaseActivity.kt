package com.yunxiao.scan.base

import android.content.res.Configuration
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity

/**
 * Activity MVP基类
 */
open class YxBaseActivity : AppCompatActivity() {

    override fun getResources(): Resources {
        //应用内字体大小不随系统字体大小变化而变化
        return super.getResources().apply {
            updateConfiguration(Configuration().apply {
                setToDefaults()
            }, displayMetrics)
        }
    }
}
