package jxf.pms.controller;

import jxf.pms.data.ProjectDTO;
import jxf.pms.data.UserBaseDTO;
import jxf.pms.service.ProjectService;
import jxf.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BugController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @GetMapping("/test/bug")
    public String list(Model model){
        return "test_list";
    }

    @GetMapping("/test/bug/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);
        return "test_add";
    }

    @GetMapping("/test/bug/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        ProjectDTO project = projectService.getById(id).getData();
        model.addAttribute("project", project);

        return "test_update";
    }


    @GetMapping("/test/bug/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        ProjectDTO project = projectService.getById(id).getData();
        model.addAttribute("project", project);

        return "test_info";
    }

}
