package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;

@Data
public class TaskAddCmd extends BaseCmd {
    @ActionLogObjectName
    private String name;
    private String taskType;
    private Integer projectId;
    private Integer moduleId;
    private Integer requirementId;
    private String describe;
    private Integer priority;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    private Integer handler;
    private Integer estimateHours;
}
