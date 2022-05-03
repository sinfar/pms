package jxf.pms.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.PermissionListQry;
import jxf.pms.cmd.UserLoginCmd;
import jxf.pms.data.LoginResultDTO;
import jxf.pms.data.LoginUserDTO;
import jxf.pms.data.PermissionDTO;
import jxf.pms.dbo.BugStatusStatDO;
import jxf.pms.dbo.RequirementStatusStatDO;
import jxf.pms.dbo.TaskStatusStatDO;
import jxf.pms.mapper.BugMapper;
import jxf.pms.mapper.ProjectMapper;
import jxf.pms.mapper.RequirementMapper;
import jxf.pms.mapper.TaskMapper;
import jxf.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class IndexRestController {

    @Resource
    private BugMapper bugMapper;
    @Resource
    private RequirementMapper requirementMapper;
    @Resource
    private TaskMapper taskMapper;

    @PostMapping("index/getTaskStatusStat")
    public SingleResponse<TaskStatusStatDO> getTaskStatusStat(){
        TaskStatusStatDO statDO = taskMapper.getStatusStat();
        return SingleResponse.of(statDO);
    }

    @PostMapping("index/getRequirementStatusStat")
    public SingleResponse<RequirementStatusStatDO> getRequirementStatusStat(){
        RequirementStatusStatDO statDO = requirementMapper.getStatusStat();
        return SingleResponse.of(statDO);
    }

    @PostMapping("index/getBugStatusStat")
    public SingleResponse<BugStatusStatDO> getBugStatusStat(){
        BugStatusStatDO statDO = bugMapper.getStatusStat();
        return SingleResponse.of(statDO);
    }
}
