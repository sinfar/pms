package jxf.pms.controller;

import jxf.pms.cmd.ModuleListQry;
import jxf.pms.data.*;
import jxf.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequirementController {

    @Autowired
    private RequirementService requirementService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private PlanService planService;

    @GetMapping("/project/requirement")
    public String list(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();

        // 所有项目
        List<ProjectDTO> projects = projectService.all();

        model.addAttribute("users", users);
        model.addAttribute("projects", projects);
        return "requirement_list";
    }

    @GetMapping("/project/requirement/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        // 所有计划
        List<PlanDTO> plans = planService.all();
        model.addAttribute("plans", plans);

        return "requirement_add";
    }

    @GetMapping("/project/requirement/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        // 需求
        RequirementDTO requirement = requirementService.getById(id).getData();
        model.addAttribute("requirement", requirement);

        // 当前模块
        ModuleListQry qry = new ModuleListQry();
        qry.setProjectId(requirement.getProjectId());
        List<ModuleDTO> modules = moduleService.list(qry).getData();
        model.addAttribute("modules", modules);

        // 所有计划
        List<PlanDTO> plans = planService.all();
        model.addAttribute("plans", plans);

        return "requirement_update";
    }


    @GetMapping("/project/requirement/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        RequirementDTO requirement = requirementService.getById(id).getData();
        model.addAttribute("requirement", requirement);

        return "requirement_info";
    }

}
