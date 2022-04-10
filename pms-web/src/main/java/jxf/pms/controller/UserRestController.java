package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.UserAddResult;
import jxf.pms.data.UserDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceI userService;

    @PostMapping("/org/user/list")
    public PageResponse<UserDTO> list(@RequestBody UserListQry userListQry){
        return userService.list(userListQry);
    }

    @PostMapping("/org/user/add")
    @ActionLog(operateObject= OperateObject.user,actionType = ActionType.create)
    public SingleResponse<UserAddResult> add(@RequestBody UserAddCmd userAddCmd){
        return userService.add(userAddCmd);
    }

    @PostMapping("/org/user/update")
    @ActionLog(operateObject= OperateObject.user,actionType = ActionType.update)
    public Response update(@RequestBody UserUpdateCmd userUpdateCmd){
        return userService.update(userUpdateCmd);
    }

    @PostMapping("/org/user/enabled")
    @ActionLog(operateObject= OperateObject.user,actionType = ActionType.enabled)
    public Response enabled(@RequestBody UserEnabledCmd cmd){
        return userService.enabled(cmd);
    }

    @PostMapping("/org/user/disabled")
    @ActionLog(operateObject= OperateObject.user,actionType = ActionType.disabled)
    public Response disabled(@RequestBody UserDisabledCmd cmd){
        return userService.disabled(cmd);
    }

    @PostMapping("/org/user/delete")
    @ActionLog(operateObject= OperateObject.user,actionType = ActionType.delete)
    public Response delete(@RequestBody UserDeleteCmd cmd){
        return userService.delete(cmd);
    }

    @PostMapping("/org/user/resetPassword")
    @ActionLog(operateObject= OperateObject.user,actionType = ActionType.resetpassword)
    public Response resetPassword(@RequestBody UserResetPasswordCmd cmd){
        return userService.resetPassword(cmd);
    }

}
