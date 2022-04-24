package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;

@Data
public class PlanAddCmd extends BaseCmd {
    @ActionLogObjectName
    private String name;
    private Date beginDate;
    private Date endDate;
    private String describe;
    private Integer projectId;
}
