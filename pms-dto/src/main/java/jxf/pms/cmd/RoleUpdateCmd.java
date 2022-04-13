package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.List;

@Data
public class RoleUpdateCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer roleId;
    @ActionLogObjectName
    private String name;
    private List<Integer> permissionIds;
}
