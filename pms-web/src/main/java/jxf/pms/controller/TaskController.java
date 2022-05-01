package jxf.pms.controller;

import jxf.pms.cmd.ModuleListQry;
import jxf.pms.cmd.RequirementListQry;
import jxf.pms.data.*;
import jxf.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private PlanService planService;
    @Autowired
    private RequirementService requirementService;

    @GetMapping("/project/task")
    public String list(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();

        // 所有项目
        List<ProjectDTO> projects = projectService.all();

        model.addAttribute("users", users);
        model.addAttribute("projects", projects);
        return "task_list";
    }

    @GetMapping("/project/task/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        return "task_add";
    }

    @GetMapping("/project/task/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        // 任务
        TaskDTO task = taskService.getById(id).getData();
        model.addAttribute("task", task);

        // 当前模块
        ModuleListQry qry = new ModuleListQry();
        qry.setProjectId(task.getProjectId());
        List<ModuleDTO> modules = moduleService.list(qry).getData();
        model.addAttribute("modules", modules);

        // 当前需求
        RequirementListQry reqQry = new RequirementListQry();
        reqQry.setProjectId(task.getProjectId());
        List<RequirementDTO> requirements = requirementService.list(reqQry).getData();
        model.addAttribute("requirements", requirements);

        return "task_update";
    }


    @GetMapping("/project/task/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        TaskDTO task = taskService.getById(id).getData();
        model.addAttribute("task", task);

        return "task_info";
    }

}
