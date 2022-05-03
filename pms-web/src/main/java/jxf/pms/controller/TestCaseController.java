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
public class TestCaseController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RequirementService requirementService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/test/testCase")
    public String list(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();

        // 所有项目
        List<ProjectDTO> projects = projectService.all();

        // 所有需求
        RequirementListQry qry = new RequirementListQry();
        qry.setPageIndex(1);
        qry.setPageSize(Integer.MAX_VALUE);
        List<RequirementDTO> requirements = requirementService.list(qry).getData();
        model.addAttribute("requirements", requirements);

        model.addAttribute("users", users);
        model.addAttribute("projects", projects);

        return "testCase_list";
    }

    @GetMapping("/test/testCase/add")
    public String add(Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);
        return "testCase_add";
    }

    @GetMapping("/test/testCase/update")
    public String update(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        // 测试用例
        TestCaseDTO testCase = testCaseService.getById(id).getData();
        model.addAttribute("testCase", testCase);

        // 当前模块
        ModuleListQry qry = new ModuleListQry();
        qry.setProjectId(testCase.getProjectId());
        List<ModuleDTO> modules = moduleService.list(qry).getData();
        model.addAttribute("modules", modules);

        // 当前需求
        RequirementListQry reqQry = new RequirementListQry();
        reqQry.setProjectId(testCase.getProjectId());
        List<RequirementDTO> requirements = requirementService.list(reqQry).getData();
        model.addAttribute("requirements", requirements);

        return "testCase_update";
    }


    @GetMapping("/test/testCase/exec")
    public String exec(@RequestParam Integer id, Model model){
        // 测试用例
        TestCaseDTO testCase = testCaseService.getById(id).getData();
        model.addAttribute("testCase", testCase);

        // 执行记录
        List<TestCaseExecDTO> testCaseExecs = testCaseService.gettTestCaseExecs(id);
        model.addAttribute("testCaseExecs", testCaseExecs);

        return "testCase_exec";
    }


    @GetMapping("/test/testCase/info")
    public String info(@RequestParam Integer id, Model model){
        // 所有用户
        List<UserBaseDTO>  users =  userService.all().getData();
        model.addAttribute("users", users);

        TestCaseDTO testCase = testCaseService.getById(id).getData();
        model.addAttribute("testCase", testCase);

        return "testCase_info";
    }

}
