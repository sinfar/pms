package jxf.pms.data;

import lombok.Data;

import java.util.Date;

@Data
public class BugDTO {
    private Integer id;
    private String name;
    private Integer testCaseId;
    private Integer projectId;
    private Integer moduleId;
    private Integer requirement_Id;
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
