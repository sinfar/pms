package jxf.pms.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.ModuleAddResult;
import jxf.pms.data.ModuleDTO;
import jxf.pms.data.ProjectAddResult;
import jxf.pms.data.ProjectDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.ModuleService;
import jxf.pms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleRestController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/project/module/list")
    public MultiResponse<ModuleDTO> list(@RequestBody ModuleListQry qry) {
        return moduleService.list(qry);
    }

    @PostMapping("/project/module/add")
    @ActionLog(operateObject = OperateObject.module, actionType = ActionType.create)
    public SingleResponse<ModuleAddResult> add(@RequestBody ModuleAddCmd cmd) {
        return moduleService.add(cmd);
    }

    @PostMapping("/project/module/rename")
    @ActionLog(operateObject = OperateObject.module, actionType = ActionType.rename)
    public Response update(@RequestBody ModuleRenameCmd cmd) {
        return moduleService.rename(cmd);
    }

    @PostMapping("/project/module/delete")
    @ActionLog(operateObject = OperateObject.module, actionType = ActionType.delete)
    public Response delete(@RequestBody ModuleDeleteCmd cmd) {
        return moduleService.delete(cmd);
    }
}
