package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;

@Data
public class TaskFinishCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    private Integer costHours;
}
