package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import jxf.pms.cmd.ActionLogListQry;
import jxf.pms.data.*;
import jxf.pms.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionLogRestController {

    @Autowired
    private ActionLogService actionLogService;

    @PostMapping("/org/log/list")
    public PageResponse<ActionLogDTO> list(@RequestBody ActionLogListQry actionLogListQry){
        return actionLogService.list(actionLogListQry);
    }
}
