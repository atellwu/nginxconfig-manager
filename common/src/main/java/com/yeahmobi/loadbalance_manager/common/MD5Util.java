package com.yeahmobi.loadbalance_manager.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static final MessageDigest md;
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error when static init MD5Util", e);
        }
    }

    private MD5Util() {
    }

    /**
     * 根据String生成MD5字符串(默认使用UTF-8编码)
     */
    public static String generateMD5(String str) {
        try {
            return generateMD5(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error when generateMD5", e);
        }
    }

    /**
     * 根据String生成MD5字符串(需要指定编码)
     */
    public static String generateMD5(String str, String charset) {
        try {
            return generateMD5(str.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error when generateMD5", e);
        }
    }

    // 根据bytes生成SHA-1字符串
    public static String generateMD5(byte[] bytes) {
        String ret = null;
        byte[] byteDigest = md.digest(bytes);
        ret = byteToString(byteDigest);
        return ret;
    }

    // 将bytes转化为String
    private static String byteToString(byte[] digest) {
        String tmpStr = "";
        StringBuffer strBuf = new StringBuffer(40);
        for (int i = 0; i < digest.length; i++) {
            tmpStr = (Integer.toHexString(digest[i] & 0xff));
            if (tmpStr.length() == 1) {
                strBuf.append("0" + tmpStr);
            } else {
                strBuf.append(tmpStr);
            }
        }
        return strBuf.toString();
    }
}
