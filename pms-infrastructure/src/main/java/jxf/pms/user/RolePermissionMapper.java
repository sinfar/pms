package jxf.pms.user;

import jxf.pms.dbo.RolePermissionAddDO;
import jxf.pms.dbo.RolePermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    void add(RolePermissionAddDO rolePermissionAddDO);

    List<RolePermissionDO> getByRoleId(Integer roleId);
}
