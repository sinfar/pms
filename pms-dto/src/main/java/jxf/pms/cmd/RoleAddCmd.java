package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.List;

@Data
public class RoleAddCmd extends BaseCmd {
    @ActionLogObjectName
    private String name;
    private List<Integer> permissionIds;
}
