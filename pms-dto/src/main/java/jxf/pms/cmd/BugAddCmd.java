package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BugAddCmd extends BaseCmd {
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
}
