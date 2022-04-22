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
public class ModuleController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/project/module")
    public String list(Model model,@RequestParam Integer id){
        ProjectDTO project = projectService.getById(id).getData();
        model.addAttribute("project", project);
        return "project_module";
    }

}
