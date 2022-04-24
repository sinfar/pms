package jxf.pms.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RequirementDTO {
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
    private String projectName;
    private String planName;
    private String moduleName;
}
