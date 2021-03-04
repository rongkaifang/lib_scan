package com.yunxiao.scan

import android.Manifest

/**
 * @author Created by rongkaifang
 * @date Created at 10:33 AM on 2020/11/13
 * @description
 */
class ScanMethodUtil {
    companion object {
        var permissions = arrayOf(Manifest.permission.CAMERA)
        const val REQUESTPERMISSION = 0x00
        const val REQUESTACTIVITYCODE = 0x01

    }
}