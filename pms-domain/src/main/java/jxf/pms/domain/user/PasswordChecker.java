package jxf.pms.domain.user;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordChecker {
    // 加密因子
    private static String factor = "yZnvJZagRLSDnCeL";
    private static PasswordEncryptor encryptor = new PasswordEncryptor(factor);

    public static boolean check(String password, String encryptPassword) {
       return encryptor.encrypt(password).equals(encryptPassword);
    }

    public static String encrypt(String password) {
        return encryptor.encrypt(password);
    }

    public static String randomPassword() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}
