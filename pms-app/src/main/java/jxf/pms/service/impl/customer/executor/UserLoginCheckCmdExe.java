
package jxf.pms.service.impl.customer.executor;

import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.UserLoginCmd;
import jxf.pms.data.ErrorCode;
import jxf.pms.dbo.LoginUserDO;
import jxf.pms.domain.user.PasswordChecker;
import jxf.pms.user.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class UserLoginCheckCmdExe {
    @Resource
    private UserMapper userMapper;
    public SingleResponse<LoginUserDO> execute(UserLoginCmd cmd) {
        String loginName = cmd.getLoginName();
        String password = cmd.getPassword();

        LoginUserDO user = userMapper.getByLoginName(loginName);
        if (user == null)
            return SingleResponse.buildFailure(ErrorCode.b_user_user_disabled.getErrCode(), ErrorCode.b_user_user_not_exists.getErrDesc());
        if (user.getStatus().equals("禁用"))
            return SingleResponse.buildFailure(ErrorCode.b_user_user_disabled.getErrCode(), ErrorCode.b_user_user_disabled.getErrDesc());
        if (!PasswordChecker.check(password, user.getPassword()))
            return SingleResponse.buildFailure(ErrorCode.b_user_password_error.getErrCode(), ErrorCode.b_user_password_error.getErrDesc());
        return SingleResponse.of(user);
    }

}
