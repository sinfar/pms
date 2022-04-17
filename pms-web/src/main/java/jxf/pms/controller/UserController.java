package jxf.pms.controller;

import jxf.pms.data.RoleBaseDTO;
import jxf.pms.data.UserDTO;
import jxf.pms.service.RoleService;
import jxf.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private RoleService roleServiceI;
    @Autowired
    private UserService userService;

    @GetMapping("org/user")
    public String list(Model model){
        // 查询所有角色
        List<RoleBaseDTO> roles = roleServiceI.all().getData();
        model.addAttribute("roles", roles);
        return "user_list";
    }

    @GetMapping("org/user/add")
    public String add(Model model){
        // 查询所有角色
        List<RoleBaseDTO> roles = roleServiceI.all().getData();
        model.addAttribute("roles", roles);
        return "user_add";
    }

    @GetMapping("org/user/update")
    public String update(@RequestParam Integer userId, Model model){
        // 查询所有角色
        List<RoleBaseDTO> roles = roleServiceI.all().getData();
        model.addAttribute("roles", roles);

        // 查询用户
        UserDTO user = userService.getById(userId).getData();
        model.addAttribute("updateUser", user);

        return "user_update";
    }
}
