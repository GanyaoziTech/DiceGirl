package com.qq.weixin.mp.aes;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * SHA1 class
 * <p>
 * 计算公众平台的消息签名接口.
 * @author derek.dai( dp419936514@gmail.com )
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class SHA1 {

    /**
     * 用SHA1算法生成安全签名
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @param encrypt   密文
     * @return 安全签名
     * @throws AesException e
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException {
        try {
            String[] array = new String[]{token, timestamp, nonce, encrypt};
            StringBuilder sb = new StringBuilder();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 4; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            return dig(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }
    }

    /**
     * 用SHA1算法生成安全签名
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 安全签名
     * @throws AesException e
     */
    public static String getSHA1(String token, String timestamp, String nonce) throws AesException {
        try {
            String[] array = new String[]{token, timestamp, nonce};
            StringBuilder sb = new StringBuilder();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            return dig(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }
    }

    private static String dig(byte[] digest) {
        StringBuilder hexStr = new StringBuilder();
        String shaHex;
        for (byte aDigest : digest) {
            shaHex = Integer.toHexString(aDigest & 0xFF);
            if (shaHex.length() < 2) {
                hexStr.append(0);
            }
            hexStr.append(shaHex);
        }
        return hexStr.toString();
    }
}
