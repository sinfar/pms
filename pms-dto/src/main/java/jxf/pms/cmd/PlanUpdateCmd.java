package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;

@Data
public class PlanUpdateCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    @ActionLogObjectName
    private String name;
    private Date beginDate;
    private Date endDate;
    private String describe;
    private Integer projectId;
}
