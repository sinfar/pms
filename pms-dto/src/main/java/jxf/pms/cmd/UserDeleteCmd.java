package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class UserDeleteCmd extends  BaseCmd {
    @ActionLogObjectId
    private Integer userId;
}
