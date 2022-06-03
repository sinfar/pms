package jxf.pms.controller;

import jxf.pms.cmd.ModuleListQry;
import jxf.pms.cmd.RequirementListQry;
import jxf.pms.data.*;
import jxf.pms.dbo.PlanDO;
import jxf.pms.service.*;
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
    private BugService bugService;
    @Autowired
    private RequirementService requirementService;

    @GetMapping("/project/plan")
    public String list(Model model){
        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);
        return "plan_list";
    }

    @GetMapping("/project/plan/add")
    public String add(Model model){
        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);
        return "plan_add";
    }

    @GetMapping("/project/plan/update")
    public String update(@RequestParam Integer id, Model model){
        PlanDTO plan = planService.getById(id).getData();
        model.addAttribute("plan", plan);

        // 所有项目
        List<ProjectDTO> projects = projectService.all();
        model.addAttribute("projects", projects);

        return "plan_update";
    }

    @GetMapping("/project/plan/info")
    public String info(@RequestParam Integer id, Model model){
        PlanDTO plan = planService.getById(id).getData();
        model.addAttribute("plan", plan);

        return "plan_info";
    }

    @GetMapping("/project/plan/requirement")
    public String requirement(@RequestParam Integer id, Model model){
        PlanDTO plan = planService.getById(id).getData();
        model.addAttribute("plan", plan);

        // 项目需求
        List<RequirementDTO> requirements = planService.getRequirements(id);
        model.addAttribute("requirements", requirements);

        // 所有需求
        RequirementListQry qry = new RequirementListQry();
        qry.setPageIndex(1);
        qry.setPageSize(Integer.MAX_VALUE);
        qry.setProjectId(plan.getProjectId());
        List<RequirementDTO> allRequirements = requirementService.list(qry).getData();
        allRequirements.removeAll(requirements);
        model.addAttribute("allRequirements", allRequirements);

        return "plan_requirement";
    }

    @GetMapping("/project/plan/bug")
    public String bug(@RequestParam Integer id, Model model){
        PlanDTO plan = planService.getById(id).getData();
        model.addAttribute("plan", plan);

        // 项目bug
        List<BugDTO> bugs = planService.getBugs(id);
        model.addAttribute("bugs", bugs);

        // 所有bug
        List<BugDTO> allBugs = bugService.all(plan.getProjectId());
        allBugs.removeAll(bugs);
        model.addAttribute("allBugs", allBugs);

        return "plan_bug";
    }

}
