package jxf.pms.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private Integer id;
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
    private Integer finisher;
    private Integer createBy;
    private Date createTime;
    private Integer estimateHours;
    private Integer costHours;
    private String taskStatus;
    private String createByName;
    private String handlerName;
    private String finisherName;
    private String projectName;
    private String moduleName;
    private String requirementName;
}
