package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.TestCaseDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCaseRestController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/test/testCase/list")
    public PageResponse<TestCaseDTO> list(@RequestBody  TestCaseListQry qry) {
        return testCaseService.list(qry);
    }

    @PostMapping("/test/testCase/add")
    @ActionLog(operateObject = OperateObject.testCase, actionType = ActionType.create)
    public SingleResponse<AddResult> add(@RequestBody TestCaseAddCmd cmd) {
        return testCaseService.add(cmd);
    }

    @PostMapping("/test/testCase/update")
    @ActionLog(operateObject = OperateObject.testCase, actionType = ActionType.update)
    public Response update(@RequestBody TestCaseUpdateCmd cmd) {
        return testCaseService.update(cmd);
    }

    @PostMapping("/test/testCase/addExec")
    @ActionLog(operateObject = OperateObject.testCase, actionType = ActionType.addExec)
    public Response addExec(@RequestBody TestCaseAddExecCmd cmd) {
        return testCaseService.addExec(cmd);
    }
}
