package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class UserEnabledCmd extends  BaseCmd {
    @ActionLogObjectId
    private Integer userId;
}
