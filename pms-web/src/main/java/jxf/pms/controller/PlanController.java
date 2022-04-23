package jxf.pms.controller;

import jxf.pms.cmd.ModuleListQry;
import jxf.pms.data.ModuleDTO;
import jxf.pms.data.ProjectDTO;
import jxf.pms.data.PlanDTO;
import jxf.pms.data.UserBaseDTO;
import jxf.pms.service.ModuleService;
import jxf.pms.service.ProjectService;
import jxf.pms.service.PlanService;
import jxf.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/project/plan")
    public String list(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();

        // 所有项目
        List<ProjectDTO> projects = projectService.all();

        model.addAttribute("users", users);
        model.addAttribute("projects", projects);
        return "plan_list";
    }

    @GetMapping("/project/plan/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();

        // 所有项目
        List<ProjectDTO> projects = projectService.all();

        // 所有计划


        model.addAttribute("users", users);
        model.addAttribute("projects", projects);
        return "plan_add";
    }

    @GetMapping("/project/plan/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        // 需求
        PlanDTO plan = planService.getById(id).getData();
        model.addAttribute("plan", plan);

        // 当前模块
        ModuleListQry qry = new ModuleListQry();
        qry.setProjectId(plan.getProjectId());
        List<ModuleDTO> modules = moduleService.list(qry).getData();
        model.addAttribute("modules", modules);

        return "plan_update";
    }


    @GetMapping("/project/plan/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        PlanDTO plan = planService.getById(id).getData();
        model.addAttribute("plan", plan);

        return "plan_info";
    }

}
