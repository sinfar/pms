package jxf.pms.mapper;

import jxf.pms.cmd.PlanListQry;
import jxf.pms.cmd.ProjectRequirementAddCmd;
import jxf.pms.dbo.PlanDO;
import jxf.pms.dbo.RequirementDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanMapper {

    List<PlanDO> list(PlanListQry qry);

    List<PlanDO> all();

    void add(PlanDO planDO);

    void update(PlanDO planDO);

    PlanDO getById(Integer id);

    void updatePlanStatus(@Param("id") Integer id, @Param("planStatus") String planStatus);

    List<RequirementDO> getRequirements(Integer id);

    void addRequirements(ProjectRequirementAddCmd cmd);
}
