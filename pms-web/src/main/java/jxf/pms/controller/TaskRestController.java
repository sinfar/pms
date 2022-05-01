package jxf.pms.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.TaskDTO;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/project/task/list")
    public PageResponse<TaskDTO> list(@RequestBody  TaskListQry qry) {
        return taskService.list(qry);
    }

    @PostMapping("/project/task/add")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.create)
    public SingleResponse<AddResult> add(@RequestBody TaskAddCmd cmd) {
        return taskService.add(cmd);
    }

    @PostMapping("/project/task/update")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.update)
    public Response update(@RequestBody TaskUpdateCmd cmd) {
        return taskService.update(cmd);
    }

    @PostMapping("/project/task/finish")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.finish)
    public Response finish(@RequestBody TaskFinishCmd cmd) {
        return taskService.finish(cmd);
    }

    @PostMapping("/project/task/pause")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.pause)
    public Response pause(@RequestBody OperateBaseCmd cmd) {
        return taskService.pause(cmd);
    }

    @PostMapping("/project/task/active")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.active)
    public Response active(@RequestBody OperateBaseCmd cmd) {
        return taskService.active(cmd);
    }

    @PostMapping("/project/task/cancel")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.cancel)
    public Response cancel(@RequestBody OperateBaseCmd cmd) {
        return taskService.cancel(cmd);
    }

    @PostMapping("/project/task/close")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.close)
    public Response close(@RequestBody OperateBaseCmd cmd) {
        return taskService.close(cmd);
    }

    @PostMapping("/project/task/start")
    @ActionLog(operateObject = OperateObject.task, actionType = ActionType.start)
    public Response start(@RequestBody OperateBaseCmd cmd) {
        return taskService.start(cmd);
    }
}
