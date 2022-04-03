package jxf.pms.domain.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    // 加密
    public static String encrypt(String sSrc, String sKey)  {
        try {
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = new byte[0];
                encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    // 解密
    public static String decrypt(String sSrc, String sKey)  {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;

        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "1234567890123456";
        // 需要加密的字串
        String cSrc = "www.gowhere.so";
        System.out.println(cSrc);
        // 加密
        String enString = AESUtil.encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = AESUtil.decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);
    }
}
