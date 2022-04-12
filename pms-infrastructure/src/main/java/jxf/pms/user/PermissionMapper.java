package jxf.pms.user;

import jxf.pms.dbo.PermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<PermissionDO> getPermissionList(@Param("userId")Integer userId);

    List<PermissionDO> all();
}
