package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

@Data
public class PlanAddCmd extends BaseCmd {
    @ActionLogObjectName
    private String name;
    private Integer projectId;
    private Integer moduleId;
    private Integer planId;
    private String describe;
    private String acceptanceCriteria;
    private Integer priority;
    private Boolean isReview;
    private Integer handleBy;
}
