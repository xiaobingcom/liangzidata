package com.sandu.common.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 未使用加盐算法
 * @author xiaobing
 * @date 2020-02-29 20:28
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 20:28     xiaobing          v1.0.0           Created
 *
 */
public class EncryptionUtil {

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 将字符串加密
     * @param str   要加密的字符串
     * @param algorithm 类型，MD5,SHA1
     * @return  返回一个32位的字符串，其中应为字符为大写
     */
    public static String encode(String str,String algorithm) {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }


}
