package jxf.pms.mapper;

import jxf.pms.dbo.RoleBaseDO;
import jxf.pms.dbo.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<RoleBaseDO> all();

    List<RoleDO> list();

    void add(RoleDO roleDO);

    RoleBaseDO info(Integer roleId);

    void updateStatus(@Param("id") Integer roleId, @Param("status") String status);

    void update(RoleDO roleDO);

    RoleBaseDO getByName(String name);
}
