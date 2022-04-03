package jxf.pms.domain.user;

import java.security.MessageDigest;

public class PasswordEncryptor {
    // 加密因子
    private String factor;

    public PasswordEncryptor(String factor) {
        this.factor = factor;
    }

    public String encrypt(String password) {
        try {
            String p = password + factor;
            byte[] bytes = MessageDigest.getInstance("md5").digest(p.getBytes("UTF-8"));
            return byteToHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * byte数组转hex
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }
}
