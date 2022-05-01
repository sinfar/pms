package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;

@Data
public class BugUpdateCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    @ActionLogObjectName
    private String name;
    private Integer projectId;
    private Integer requirementId;
    private Integer moduleId;
    private Integer taskId;
    private Integer handler;
    private String describe;
    private String bugType;
    private String severityType;
    private String bugStatus;
}
