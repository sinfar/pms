package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import jxf.pms.cmd.UserListQry;
import jxf.pms.data.UserDTO;
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
    public Response add(@RequestBody UserListQry userListQry){
        return userService.list(userListQry);
    }

}
