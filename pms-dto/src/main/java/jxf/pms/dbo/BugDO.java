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
    private Integer bugType;
    private Integer describe;
    private Integer severityType;
    private Integer solutionType;
    private Integer solveBy;
    private Date solveTime;
    private String bugStatus;
    private Integer createBy;
    private Date createTime;
}
