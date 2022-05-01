package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;

@Data
public class TaskListQry extends PageQuery {

    private Integer id;
    private String name;
    private String taskType;
    private String taskStatus;
    private Integer projectId;
    private Integer handler;
    private Integer createBy;
}
