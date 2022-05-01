package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class BugDO {
    private Integer id;
    private String name;
    private Integer testCaseId;
    private Integer projectId;
    private Integer moduleId;
    private Integer requirementId;
    private Integer taskId;
    private Integer handler;
    private String bugType;
    private String describe;
    private String severityType;
    private String solutionType;
    private Integer solveBy;
    private Date solveTime;
    private String bugStatus;
    private Integer createBy;
    private Date createTime;

    private String createByName;
    private String handlerName;
    private String solveByName;
    private String projectName;
    private String moduleName;
    private String requirementName;
    private String taskName;
}
