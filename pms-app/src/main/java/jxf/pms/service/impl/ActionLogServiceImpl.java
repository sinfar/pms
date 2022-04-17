package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.*;
import jxf.pms.dbo.*;
import jxf.pms.domain.user.PasswordChecker;
import jxf.pms.service.ActionLogService;
import jxf.pms.mapper.ActionLogMapper;
import jxf.pms.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
@CatchAndLog
public class ActionLogServiceImpl implements ActionLogService {

    @Resource
    private ActionLogMapper actionLogMapper;

    @Override
    public PageResponse<ActionLogDTO> list(ActionLogListQry actionLogListQry) {
        PageHelper.startPage(actionLogListQry.getPageIndex(), actionLogListQry.getPageSize());
        List<ActionLogDO> list = actionLogMapper.list(actionLogListQry);
        PageInfo<ActionLogDO> page = new PageInfo(list);

        List<ActionLogDTO> actionLogDTOS = new ArrayList<>();
        for(ActionLogDO actionLogDO: list) {
            ActionLogDTO actionLogDTO = new ActionLogDTO();
            BeanUtils.copyProperties(actionLogDO, actionLogDTO);
            actionLogDTOS.add(actionLogDTO);
        }

        return  PageResponse.of(actionLogDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Component
    public static class UserLoginCheckCmdExe {
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
}
