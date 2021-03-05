package com.yunxiao.scan.util;

import android.Manifest;

import java.util.Arrays;

/**
 * @author Created by rongkaifang
 * @date Created at 4:50 PM on 2021/3/4
 * @description
 */
public class ScanMethodUtil {

    final static String[] permissions = new String[]{Manifest.permission.CAMERA};
    public final static int REQUESTPERMISSION = 0x00;
    public final static int REQUESTACTIVITYCODE = 0x01;

}
