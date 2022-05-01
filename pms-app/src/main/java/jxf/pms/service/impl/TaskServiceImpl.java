package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.TaskDTO;
import jxf.pms.dbo.TaskDO;
import jxf.pms.mapper.TaskMapper;
import jxf.pms.service.TaskService;
import jxf.pms.util.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    @Override
    public PageResponse<TaskDTO> list(TaskListQry qry) {
        PageHelper.startPage(qry.getPageIndex(), qry.getPageSize());
        List<TaskDO> list = taskMapper.list(qry);
        PageInfo<TaskDTO> page = new PageInfo(list);

        List<TaskDTO> taskDTOS = ObjectUtils.copyList(list, TaskDTO.class);

        return  PageResponse.of(taskDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public SingleResponse<AddResult> add(TaskAddCmd cmd) {
        TaskDO taskDO = new TaskDO();
        BeanUtils.copyProperties(cmd, taskDO);
        taskDO.setCreateTime(new Date());
        taskDO.setCreateBy(cmd.getLoginUserId());
        taskDO.setTaskStatus("未开始");
        taskMapper.add(taskDO);

        AddResult result = new AddResult();
        result.setId(taskDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(TaskUpdateCmd cmd) {
        TaskDO taskDO = new TaskDO();
        BeanUtils.copyProperties(cmd, taskDO);
        taskMapper.update(taskDO);

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<TaskDTO> getById(@Param("id") Integer id) {
        TaskDO taskDO =  taskMapper.getById(id);
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(taskDO, taskDTO);

        return SingleResponse.of(taskDTO);
    }

    @Override
    public Response close(OperateBaseCmd cmd) {
        updateTaskStatus(cmd.getId(), "已关闭", cmd.getLoginUserId());
        return Response.buildSuccess();
    }

    @Override
    public Response pause(OperateBaseCmd cmd) {
        updateTaskStatus(cmd.getId(), "已暂停", cmd.getLoginUserId());
        return Response.buildSuccess();
    }

    @Override
    public Response cancel(OperateBaseCmd cmd) {
        updateTaskStatus(cmd.getId(), "已取消", cmd.getLoginUserId());
        return Response.buildSuccess();
    }

    @Override
    public Response active(OperateBaseCmd cmd) {
        updateTaskStatus(cmd.getId(), "进行中", cmd.getLoginUserId());
        return Response.buildSuccess();
    }

    @Override
    public Response finish(TaskFinishCmd cmd) {
        updateTaskStatus(cmd.getId(), "已完成", cmd.getLoginUserId(), cmd.getCostHours());
        return Response.buildSuccess();
    }

    @Override
    public Response start(OperateBaseCmd cmd) {
        updateTaskStatus(cmd.getId(), "进行中", cmd.getLoginUserId());
        return Response.buildSuccess();    }

    private void updateTaskStatus(Integer id, String taskStatus, Integer operateUserId) {
        taskMapper.updateTaskStatus(id, taskStatus, operateUserId, null);
    }

    private void updateTaskStatus(Integer id, String taskStatus, Integer operateUserId, Integer costTime) {
        taskMapper.updateTaskStatus(id, taskStatus, operateUserId, costTime);
    }
}
