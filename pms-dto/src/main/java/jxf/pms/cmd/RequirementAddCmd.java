package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RequirementAddCmd extends BaseCmd {
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
