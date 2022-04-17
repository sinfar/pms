package jxf.pms.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.PermissionListQry;
import jxf.pms.cmd.UserLoginCmd;
import jxf.pms.data.LoginResultDTO;
import jxf.pms.data.LoginUserDTO;
import jxf.pms.data.PermissionDTO;
import jxf.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginRestController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Response login(@RequestBody UserLoginCmd loginCmd, HttpServletRequest request, HttpServletResponse response){
        SingleResponse<LoginResultDTO> rst = userService.login(loginCmd);
        if (!rst.isSuccess())
            return rst;

//        // 登录成功设置cookie
//        String token = rst.getData();
//        if (rst.isSuccess()) {
//            Cookie c = new Cookie("pms-session", token);
//            c.setMaxAge(1000 * 60 * 60 * 12);
//            response.addCookie(c);
//        }

        // 设置权限
        LoginUserDTO user = rst.getData().getUser();
        PermissionListQry permissionListQry = new PermissionListQry();
        permissionListQry.setUserId(user.getId());
        MultiResponse<PermissionDTO> permissions = userService.getPermissionList(permissionListQry);
        request.getSession().setAttribute("permissions", permissions.getData());
        request.getSession().setAttribute("user", user);

        return Response.buildSuccess();

    }

    @PostMapping("logout")
    public Response logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("permissions");
        request.getSession().removeAttribute("user");
        return Response.buildSuccess();

    }
}
