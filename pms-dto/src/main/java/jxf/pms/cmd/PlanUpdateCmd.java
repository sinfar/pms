package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    private String describe;
    private Integer projectId;
}
