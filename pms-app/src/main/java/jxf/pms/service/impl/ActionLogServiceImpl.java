package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.*;
import jxf.pms.dbo.*;
import jxf.pms.domain.user.LoginToken;
import jxf.pms.domain.user.PasswordChecker;
import jxf.pms.service.ActionLogServiceI;
import jxf.pms.service.UserServiceI;
import jxf.pms.service.impl.customer.executor.UserLoginCheckCmdExe;
import jxf.pms.user.ActionLogMapper;
import jxf.pms.user.PermissionMapper;
import jxf.pms.user.RoleMapper;
import jxf.pms.user.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class ActionLogServiceImpl implements ActionLogServiceI {

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


}
