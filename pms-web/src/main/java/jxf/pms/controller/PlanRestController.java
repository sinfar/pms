package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.PlanDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanRestController {

    @Autowired
    private PlanService planService;

    @PostMapping("/project/plan/list")
    public PageResponse<PlanDTO> list(@RequestBody  PlanListQry qry) {
        return planService.list(qry);
    }

    @PostMapping("/project/plan/add")
    @ActionLog(operateObject = OperateObject.plan, actionType = ActionType.create)
    public SingleResponse<AddResult> add(@RequestBody PlanAddCmd cmd) {
        return planService.add(cmd);
    }

    @PostMapping("/project/plan/update")
    @ActionLog(operateObject = OperateObject.plan, actionType = ActionType.update)
    public Response update(@RequestBody PlanUpdateCmd cmd) {
        return planService.update(cmd);
    }

    @PostMapping("/project/plan/delete")
    @ActionLog(operateObject = OperateObject.plan, actionType = ActionType.delete)
    public Response delete(@RequestBody OperateBaseCmd cmd) {
        return planService.delete(cmd);
    }

    @PostMapping("/project/plan/addRequirements")
    @ActionLog(operateObject = OperateObject.plan, actionType = ActionType.delete)
    public Response addRequirements(@RequestBody ProjectRequirementAddCmd cmd) {
        return planService.addRequirements(cmd);
    }
}
