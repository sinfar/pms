package jxf.pms.domain.user;

public class PasswordChecker {
    // 加密因子
    private static String factor = "yZnvJZagRLSDnCeL";
    private static PasswordEncryptor encryptor = new PasswordEncryptor(factor);

    public static boolean check(String password, String encryptPassword) {
       return encryptor.encrypt(password).equals(encryptPassword);
    }
}
