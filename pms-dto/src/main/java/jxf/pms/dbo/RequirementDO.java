package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class RequirementDO {
    private Integer id;
    private String name;
    private Integer projectId;
    private Integer moduleId;
    private Integer planId;
    private String describe;
    private String acceptanceCriteria;
    private Integer priority;
    private Integer createBy;
    private Date createTime;
    private String requirementPhase;
    private String requirementStatus;
    private String createByName;
    private Integer handleBy;
    private String handleByName;
}
