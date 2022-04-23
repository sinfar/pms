package jxf.pms.mapper;

import jxf.pms.cmd.ProjectListQry;
import jxf.pms.cmd.RequirementListQry;
import jxf.pms.dbo.ProjectDO;
import jxf.pms.dbo.RequirementDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RequirementMapper {

    List<RequirementDO> list(RequirementListQry qry);

    void add(RequirementDO requirementDO);

    void update(RequirementDO requirementDO);

    RequirementDO getById(Integer id);

    void updateRequirementStatus(@Param("id") Integer id, @Param("requirementStatus") String requirementStatus);
}
