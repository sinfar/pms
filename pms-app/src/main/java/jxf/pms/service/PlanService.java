package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.BugDTO;
import jxf.pms.data.PlanDTO;
import jxf.pms.data.RequirementDTO;

import java.util.List;

public interface PlanService {
    PageResponse<PlanDTO> list(PlanListQry qry);

    List<PlanDTO> all();

    SingleResponse<AddResult> add(PlanAddCmd cmd);

    Response update(PlanUpdateCmd cmd);

    SingleResponse<PlanDTO> getById(Integer id);

    Response delete(OperateBaseCmd cmd);

    List<RequirementDTO> getRequirements(Integer id);

    Response addRequirements(ProjectRequirementAddCmd cmd);

    List<BugDTO> getBugs(Integer id);

    Response addBugs(ProjectBugAddCmd cmd);
}
