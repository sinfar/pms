package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

@Data
public class RequirementUpdateCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    @ActionLogObjectName
    private String name;
    private Integer projectId;
    private Integer moduleId;
    private Integer planId;
    private String describe;
    private String acceptanceCriteria;
    private Integer priority;
    private String requirementPhase;
    private String requirementStatus;
    private Integer handleBy;
}
