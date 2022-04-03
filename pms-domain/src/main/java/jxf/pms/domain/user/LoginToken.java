package jxf.pms.domain.user;

import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.domain.util.AESUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户登录凭证
 */
public class LoginToken {

    // 加密因子
    private static String factor = "yZnvJZagRLSDnCeL";

    // token失效时间
    public static  long tokenInvalidTimes = 1000 * 60 * 60 * 12;


    /**
     * 获取Token
     * @param userName
     * @param timestamp
     * @return
     */
    public static String geToken(String userName, long timestamp) {
        String s = userName + ":" + timestamp;
        String token = AESUtil.encrypt(s, factor);
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static SingleResponse<String> checkToken(String token)  {
        // 解密
        try {
            String s = AESUtil.decrypt(token, factor);
            String[] arr = s.split(":");
            if (arr.length != 2) {
                return SingleResponse.buildFailure(null, null);
            }
            String userName = arr[0];
            String timestamp = arr[1];
            if (StringUtils.isNumeric(timestamp)) {
                return SingleResponse.buildFailure(null, null);
            }

            long time= Long.parseLong(timestamp);
            long now = System.currentTimeMillis();
            // 超时
            if (now - time > tokenInvalidTimes)
                return SingleResponse.buildFailure(null, null);

            return SingleResponse.of(userName);
        } catch (Exception e) {
            return SingleResponse.buildFailure(null, null);
        }
    }

    public class TokenCheckResult {
        public boolean success;
        public String loginName;

        public TokenCheckResult(boolean success, String loginName) {
            this.success = success;
            this.loginName = loginName;
        }
    }
}
