package jxf.pms.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.ActionLogListQry;
import jxf.pms.cmd.PermissionListQry;
import jxf.pms.cmd.UserListQry;
import jxf.pms.cmd.UserLoginCmd;
import jxf.pms.data.*;
import jxf.pms.service.ActionLogServiceI;
import jxf.pms.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ActionLogRestController {

    @Autowired
    private ActionLogServiceI actionLogService;

    @PostMapping("/org/log/list")
    public PageResponse<ActionLogDTO> list(@RequestBody ActionLogListQry actionLogListQry){
        return actionLogService.list(actionLogListQry);
    }
}
