package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RolePermissionAddDO {
    private Integer roleId;
    private List<Integer> permissionIds;
    private Integer createBy;
    private Date createTime;

}
