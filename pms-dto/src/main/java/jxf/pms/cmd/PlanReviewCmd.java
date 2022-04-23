package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class PlanReviewCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    private boolean isOk;
}
