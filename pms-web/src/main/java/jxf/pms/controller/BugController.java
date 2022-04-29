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

    @GetMapping("/project/project")
    public String list(Model model){
        return "project_list";
    }

    @GetMapping("/project/project/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);
        return "project_add";
    }

    @GetMapping("/project/project/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        ProjectDTO project = projectService.getById(id).getData();
        model.addAttribute("project", project);

        return "project_update";
    }


    @GetMapping("/project/project/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        ProjectDTO project = projectService.getById(id).getData();
        model.addAttribute("project", project);

        return "project_info";
    }

}
