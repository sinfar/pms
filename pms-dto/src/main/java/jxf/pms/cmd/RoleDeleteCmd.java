package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class RoleDeleteCmd extends  BaseCmd {
    @ActionLogObjectId
    private Integer roleId;
}
