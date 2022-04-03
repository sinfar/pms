package jxf.pms.data;

public enum ErrorCode{
    b_user_user_not_exists("b_user_user_not_exists", "用户不存在"),
    b_user_user_disabled("b_user_user_disabled", "用户已被禁用"),
    b_user_password_error("b_user_password_error", "密码错误"),
    b_user_none_login("b_user_none_login", "用户没有登录"),
    b_user_token_error("b_user_token_error", "用户token错误");

    private final String errCode;
    private final String errDesc;

    private ErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }
}
