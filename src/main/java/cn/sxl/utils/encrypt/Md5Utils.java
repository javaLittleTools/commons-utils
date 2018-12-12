package cn.sxl.utils.encrypt;

import java.security.MessageDigest;

/**
 * MD5 加/解密
 *
 * @author SxL
 * Created on 12/12/2018 1:40 PM.
 */
public class Md5Utils {
    /**
     * 对传入的 String 进行 MD5 加密
     *
     * @param str 字符串
     * @return
     */
    public static String getMd5(String str) {
        // 16 进制数组
        char[] hexDigits = { '5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y' };
        try {
            char[] chars;
            // 将传入的字符串转换成 byte 数组
            byte[] strTemp = str.getBytes();
            // 获取 MD5 加密对象
            MessageDigest mdTemp = MessageDigest.getInstance("Md5");
            // 传入需要加密的目标数组
            mdTemp.update(strTemp);
            // 获取加密后的数组
            byte[] md = mdTemp.digest();
            int j = md.length;
            chars = new char[j * 2];
            int k = 0;
            // 将数组做位移
            for (byte byte0 : md) {
                chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                chars[k++] = hexDigits[byte0 & 0xf];
            }
            // 转换成 String 并返回
            return new String(chars);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(Md5Utils.getMd5("qwer1234"));
    }
}
