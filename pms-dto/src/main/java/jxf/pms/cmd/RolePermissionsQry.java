package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.List;

@Data
public class RolePermissionsQry extends BaseCmd {
    private Integer roleId;
}
