package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.BugDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BugRestController {

    @Autowired
    private BugService bugService;

    @PostMapping("/test/bug/list")
    public PageResponse<BugDTO> list(@RequestBody  BugListQry qry) {
        return bugService.list(qry);
    }

    @PostMapping("/test/bug/add")
    @ActionLog(operateObject = OperateObject.bug, actionType = ActionType.create)
    public SingleResponse<AddResult> add(@RequestBody BugAddCmd cmd) {
        return bugService.add(cmd);
    }

    @PostMapping("/test/bug/update")
    @ActionLog(operateObject = OperateObject.bug, actionType = ActionType.update)
    public Response update(@RequestBody BugUpdateCmd cmd) {
        return bugService.update(cmd);
    }

    @PostMapping("/test/bug/resolve")
    @ActionLog(operateObject = OperateObject.bug, actionType = ActionType.resolve)
    public Response resolve(@RequestBody BugResolveCmd cmd) {
        return bugService.resolve(cmd);
    }

    @PostMapping("/test/bug/close")
    @ActionLog(operateObject = OperateObject.bug, actionType = ActionType.close)
    public Response close(@RequestBody OperateBaseCmd cmd) {
        return bugService.close(cmd);
    }

    @PostMapping("/test/bug/active")
    @ActionLog(operateObject = OperateObject.bug, actionType = ActionType.active)
    public Response active(@RequestBody OperateBaseCmd cmd) {
        return bugService.active(cmd);
    }
}
