package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.TaskDTO;

public interface TaskService {
    PageResponse<TaskDTO> list(TaskListQry qry);

    SingleResponse<AddResult> add(TaskAddCmd cmd);

    Response update(TaskUpdateCmd cmd);

    SingleResponse<TaskDTO> getById(Integer id);

    Response close(OperateBaseCmd cmd);

    Response cancel(OperateBaseCmd cmd);

    Response pause(OperateBaseCmd cmd);

    Response active(OperateBaseCmd cmd);

    Response finish(TaskFinishCmd cmd);

    Response start(OperateBaseCmd cmd);
}
