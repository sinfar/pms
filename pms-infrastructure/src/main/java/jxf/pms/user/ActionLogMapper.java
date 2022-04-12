package jxf.pms.user;

import jxf.pms.cmd.ActionLogListQry;
import jxf.pms.dbo.ActionLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActionLogMapper {
    String getObjectNameByObjectId(@Param("operateObject")String operateObject, @Param("objectId")Integer objectId);

    void add(ActionLogDO log);

    List<ActionLogDO> list(ActionLogListQry actionLogListQry);
}
