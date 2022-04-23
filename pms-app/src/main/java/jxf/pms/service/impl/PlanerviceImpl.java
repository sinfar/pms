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
import jxf.pms.dbo.PlanDO;
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
        planMapper.add(planDO);

        AddResult result = new AddResult();
        result.setId(planDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(PlanUpdateCmd cmd) {
        PlanDO planDO = new PlanDO();
        BeanUtils.copyProperties(cmd, planDO);
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
    public Response review(PlanReviewCmd cmd) {
        if (cmd.isOk())
            updatePlanStatus(cmd.getId(), "已激活");
        else
            updatePlanStatus(cmd.getId(), "草稿");
        return Response.buildSuccess();
    }

    @Override
    public Response close(OperateBaseCmd cmd) {
        updatePlanStatus(cmd.getId(), "已关闭");
        return Response.buildSuccess();
    }

    private void updatePlanStatus(Integer id, String planStatus) {
        planMapper.updatePlanStatus(id, planStatus);
    }
}
