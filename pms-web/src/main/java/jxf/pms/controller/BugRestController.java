package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.OperateBaseCmd;
import jxf.pms.cmd.ProjectAddCmd;
import jxf.pms.cmd.ProjectListQry;
import jxf.pms.cmd.ProjectUpdateCmd;
import jxf.pms.data.ProjectAddResult;
import jxf.pms.data.ProjectDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BugRestController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project/project/list")
    public PageResponse<ProjectDTO> list(@RequestBody  ProjectListQry qry) {
        return projectService.list(qry);
    }

    @PostMapping("/project/project/add")
    @ActionLog(operateObject = OperateObject.project, actionType = ActionType.create)
    public SingleResponse<ProjectAddResult> add(@RequestBody ProjectAddCmd cmd) {
        return projectService.add(cmd);
    }

    @PostMapping("/project/project/update")
    @ActionLog(operateObject = OperateObject.project, actionType = ActionType.update)
    public Response update(@RequestBody ProjectUpdateCmd cmd) {
        return projectService.update(cmd);
    }

    @PostMapping("/project/project/start")
    @ActionLog(operateObject = OperateObject.project, actionType = ActionType.start)
    public Response start(@RequestBody OperateBaseCmd cmd) {
        return projectService.start(cmd);
    }

    @PostMapping("/project/project/block")
    @ActionLog(operateObject = OperateObject.project, actionType = ActionType.block)
    public Response block(@RequestBody OperateBaseCmd cmd) {
        return projectService.block(cmd);

    }
    @PostMapping("/project/project/close")
    @ActionLog(operateObject = OperateObject.project, actionType = ActionType.close)
    public Response close(@RequestBody OperateBaseCmd cmd) {
        return projectService.close(cmd);
    }

    @PostMapping("/project/project/active")
    @ActionLog(operateObject = OperateObject.project, actionType = ActionType.active)
    public Response active(@RequestBody OperateBaseCmd cmd) {
        return projectService.active(cmd);
    }
}
