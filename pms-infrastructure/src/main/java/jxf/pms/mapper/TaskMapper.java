package jxf.pms.mapper;

import jxf.pms.cmd.TaskListQry;
import jxf.pms.dbo.TaskDO;
import jxf.pms.dbo.TaskStatusStatDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {

    List<TaskDO> list(TaskListQry qry);

    void add(TaskDO taskDO);

    void update(TaskDO taskDO);

    TaskDO getById(Integer id);

    void updateTaskStatus(@Param("id") Integer id, @Param("taskStatus") String taskStatus,
                          @Param("operateUserId") Integer operateUserId,
                          @Param("costHours") Integer costHours);

    TaskStatusStatDO getStatusStat();
}
