package com.yunxiao.scan.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5Utils {

    /**
     * MD5加密，默认小写
     * @param data 需要加密的数据
     * @return 加密以后的数据
     */
    public static String toMd5(String data) {
        return toMd5(data, false);
    }

    /**
     * MD5加密
     *
     * @param data 需要加密的数据
     * @param isCapital 是否大写
     * @return 加密以后的数据
     */
    public static String toMd5(String data, boolean isCapital) {
        try {
            return toMd5(data.getBytes("utf-8"), isCapital);
        } catch (UnsupportedEncodingException e) {
            LogUtils.d(MD5Utils.class.getSimpleName(),e);
        }
        return null;
    }

    /**
     * MD5加密
     * @param data 需要加密的数据
     * @param isCapital 是否大写
     * @return 加密以后的数据
     */
    public static String toMd5(byte[] data, boolean isCapital) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data);
            return toHexString(messageDigest.digest(), "", isCapital);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            throw new IllegalStateException(localNoSuchAlgorithmException);
        }
    }

    private static String toHexString(byte[] data, String separator, boolean isCapital) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int k : data) {
            String str = Integer.toHexString(0xFF & k);
            if (isCapital) {
                str = str.toUpperCase();
            }
            if (str.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(str).append(separator);
        }
        return stringBuilder.toString();
    }

}
