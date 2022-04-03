package jxf.pms.controller;

import jxf.pms.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {

    @Autowired
    private UserServiceI userService;

    @GetMapping("project/project")
    public String project(Model model){
        return "project_list";
    }
}
