package jxf.pms.user;

import jxf.pms.dbo.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<RoleDO> list();
}
