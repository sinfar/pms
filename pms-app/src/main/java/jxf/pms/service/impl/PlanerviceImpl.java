package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.PlanDTO;
import jxf.pms.data.RequirementDTO;
import jxf.pms.dbo.PlanDO;
import jxf.pms.dbo.RequirementDO;
import jxf.pms.mapper.PlanMapper;
import jxf.pms.service.PlanService;
import jxf.pms.util.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class PlanerviceImpl implements PlanService {
    @Resource
    private PlanMapper planMapper;

    @Override
    public PageResponse<PlanDTO> list(PlanListQry qry) {
        PageHelper.startPage(qry.getPageIndex(), qry.getPageSize());
        List<PlanDO> list = planMapper.list(qry);
        PageInfo<PlanDTO> page = new PageInfo(list);

        List<PlanDTO> planDTOS = ObjectUtils.copyList(list, PlanDTO.class);

        return  PageResponse.of(planDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public List<PlanDTO> all() {
        List<PlanDO> list = planMapper.all();
        List<PlanDTO> planDTOS = ObjectUtils.copyList(list, PlanDTO.class);
        return planDTOS;
    }

    @Override
    public SingleResponse<AddResult> add(PlanAddCmd cmd) {
        PlanDO planDO = new PlanDO();
        BeanUtils.copyProperties(cmd, planDO);
        planDO.setCreateTime(new Date());
        planDO.setCreateBy(cmd.getLoginUserId());
        planDO.setPlanStatus(cmd.getEndDate().after(new Date()) ? "未过期":"已过期");
        planMapper.add(planDO);

        AddResult result = new AddResult();
        result.setId(planDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(PlanUpdateCmd cmd) {
        PlanDO planDO = new PlanDO();
        BeanUtils.copyProperties(cmd, planDO);
        planDO.setPlanStatus(cmd.getEndDate().after(new Date()) ? "未过期":"已过期");
        planMapper.update(planDO);

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<PlanDTO> getById(@Param("id") Integer id) {
        PlanDO planDO =  planMapper.getById(id);
        PlanDTO planDTO = new PlanDTO();
        BeanUtils.copyProperties(planDO, planDTO);

        return SingleResponse.of(planDTO);
    }

    @Override
    public Response delete(OperateBaseCmd cmd) {
        updatePlanStatus(cmd.getId(), "删除");
        return Response.buildSuccess();
    }

    @Override
    public List<RequirementDTO> getRequirements(Integer id) {
        List<RequirementDO> requirementDOS = planMapper.getRequirements(id);
        return ObjectUtils.copyList(requirementDOS, RequirementDTO.class);
    }

    @Override
    public Response addRequirements(ProjectRequirementAddCmd cmd) {
        planMapper.addRequirements(cmd);
        return Response.buildSuccess();
    }

    private void updatePlanStatus(Integer id, String planStatus) {
        planMapper.updatePlanStatus(id, planStatus);
    }
}
