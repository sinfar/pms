package jxf.pms.controller;

import jxf.pms.data.RoleDTO;
import jxf.pms.data.UserBaseDTO;
import jxf.pms.data.UserDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.service.UserServiceI;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ActionLogController {

    @Autowired
    private UserServiceI userService;

    @GetMapping("org/log")
    public String list(Model model){
        // 对象类型
        List<OperateObject> operateObjects = EnumUtils.getEnumList(OperateObject.class);
        List<ActionType> actionTypes = EnumUtils.getEnumList(ActionType.class);

        // 所有用户
        List<UserBaseDTO> users = userService.all().getData();

        model.addAttribute("operateObjects", operateObjects);
        model.addAttribute("actionTypes", actionTypes);
        model.addAttribute("users", users);


        return "log_list";
    }
}
