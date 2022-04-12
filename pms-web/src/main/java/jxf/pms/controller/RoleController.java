package jxf.pms.controller;

import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.data.PermissionDTO;
import jxf.pms.data.RoleBaseDTO;
import jxf.pms.data.RoleDTO;
import jxf.pms.data.UserBaseDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.service.RoleServiceI;
import jxf.pms.service.UserServiceI;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleServiceI roleService;

    @GetMapping("/org/role")
    public String list(Model model){
        return "role_list";
    }

    @GetMapping("org/role/add")
    public String add(Model model){
        return "role_add";
    }

    @GetMapping("org/role/update")
    public String update(@RequestParam Integer roleId, Model model){
        SingleResponse<RoleBaseDTO> role = roleService.info(roleId);

        model.addAttribute("role", role.getData());
        return "role_update";
    }

}
