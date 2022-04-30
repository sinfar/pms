package jxf.pms.mapper;

import jxf.pms.cmd.BugListQry;
import jxf.pms.dbo.BugDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BugMapper {

    List<BugDO> list(BugListQry qry);

    void add(BugDO bugDO);

    void update(BugDO bugDO);

    BugDO getById(Integer id);

    void updateBugStatus(@Param("id") Integer id, @Param("bugStatus") String bugStatus);

    List<BugDO> all(@Param("projectId")Integer projectId);
}
