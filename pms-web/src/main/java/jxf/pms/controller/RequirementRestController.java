package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.RequirementDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequirementRestController {

    @Autowired
    private RequirementService requirementService;

    @PostMapping("/project/requirement/list")
    public PageResponse<RequirementDTO> list(@RequestBody  RequirementListQry qry) {
        return requirementService.list(qry);
    }

    @PostMapping("/project/requirement/add")
    @ActionLog(operateObject = OperateObject.requirement, actionType = ActionType.create)
    public SingleResponse<AddResult> add(@RequestBody RequirementAddCmd cmd) {
        return requirementService.add(cmd);
    }

    @PostMapping("/project/requirement/update")
    @ActionLog(operateObject = OperateObject.requirement, actionType = ActionType.update)
    public Response update(@RequestBody RequirementUpdateCmd cmd) {
        return requirementService.update(cmd);
    }

    @PostMapping("/project/requirement/review")
    @ActionLog(operateObject = OperateObject.requirement, actionType = ActionType.review)
    public Response review(@RequestBody RequirementReviewCmd cmd) {
        return requirementService.review(cmd);
    }

    @PostMapping("/project/requirement/close")
    @ActionLog(operateObject = OperateObject.requirement, actionType = ActionType.close)
    public Response close(@RequestBody OperateBaseCmd cmd) {
        return requirementService.close(cmd);
    }
}
