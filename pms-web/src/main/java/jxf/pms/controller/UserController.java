package jxf.pms.controller;

import jxf.pms.data.RoleDTO;
import jxf.pms.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private RoleServiceI roleServiceI;

    @GetMapping("org/user")
    public String list(Model model){
        // 查询所有角色
        List<RoleDTO> roles = roleServiceI.list().getData();
        model.addAttribute("roles", roles);
        return "user_list";
    }

    @GetMapping("org/user/add")
    public String add(Model model){
        // 查询所有角色
        List<RoleDTO> roles = roleServiceI.list().getData();
        model.addAttribute("roles", roles);
        return "user_add";
    }
}
