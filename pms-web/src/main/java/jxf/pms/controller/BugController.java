package jxf.pms.controller;

import jxf.pms.cmd.ModuleListQry;
import jxf.pms.cmd.RequirementListQry;
import jxf.pms.cmd.TaskListQry;
import jxf.pms.data.*;
import jxf.pms.service.*;
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
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RequirementService requirementService;
    @Autowired
    private BugService bugService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/test/bug")
    public String list(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();

        // 所有项目
        List<ProjectDTO> projects = projectService.all();

        model.addAttribute("users", users);
        model.addAttribute("projects", projects);

        return "bug_list";
    }

    @GetMapping("/test/bug/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);
        return "bug_add";
    }

    @GetMapping("/test/bug/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        // 任务
        BugDTO bug = bugService.getById(id).getData();
        model.addAttribute("bug", bug);

        // 当前模块
        ModuleListQry qry = new ModuleListQry();
        qry.setProjectId(bug.getProjectId());
        List<ModuleDTO> modules = moduleService.list(qry).getData();
        model.addAttribute("modules", modules);

        // 当前需求
        RequirementListQry reqQry = new RequirementListQry();
        reqQry.setProjectId(bug.getProjectId());
        List<RequirementDTO> requirements = requirementService.list(reqQry).getData();
        model.addAttribute("requirements", requirements);

        // 当前任务
        TaskListQry taskQry = new TaskListQry();
        taskQry.setProjectId(bug.getProjectId());
        List<TaskDTO> tasks = taskService.list(taskQry).getData();
        model.addAttribute("tasks", tasks);

        return "bug_update";
    }


    @GetMapping("/test/bug/resolve")
    public String resolve(@RequestParam Integer id, Model model){

        // 任务
        BugDTO bug = bugService.getById(id).getData();
        model.addAttribute("bug", bug);

        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        return "bug_resolve";
    }


    @GetMapping("/test/bug/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        BugDTO bug = bugService.getById(id).getData();
        model.addAttribute("bug", bug);

        return "bug_info";
    }

}
