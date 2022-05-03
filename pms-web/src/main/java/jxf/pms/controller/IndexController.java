package jxf.pms.controller;

import jxf.pms.data.LoginUserDTO;
import jxf.pms.dbo.MyTaskStatDO;
import jxf.pms.dbo.ProjectStatDO;
import jxf.pms.mapper.ProjectMapper;
import jxf.pms.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProjectMapper projectMapper;

    @GetMapping(value = "/index")
    public String index(Model model, HttpServletRequest request){
        // 我的待办
        LoginUserDTO user = (LoginUserDTO)request.getSession().getAttribute("user");
        MyTaskStatDO mytaskStat = userMapper.getMyTaskStat(user.getId());
        model.addAttribute("mytaskStat", mytaskStat);

        // 项目统计
        List<ProjectStatDO> projectStats = projectMapper.getProjectStat();
        model.addAttribute("projectStats", projectStats);

        return "index";
    }
}
