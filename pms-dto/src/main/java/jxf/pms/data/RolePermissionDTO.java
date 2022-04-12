package jxf.pms.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermissionDTO implements Serializable {
    private  Integer roleId;
    private Integer permissionId;
}
