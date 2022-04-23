package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.AddResult;
import jxf.pms.data.RequirementDTO;
import jxf.pms.data.UserBaseDTO;
import jxf.pms.dbo.RequirementDO;
import jxf.pms.dbo.UserBaseDO;
import jxf.pms.mapper.RequirementMapper;
import jxf.pms.service.RequirementService;
import jxf.pms.service.RequirementService;
import jxf.pms.util.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class RequirementServiceImpl implements RequirementService {
    @Resource
    private RequirementMapper requirementMapper;

    @Override
    public PageResponse<RequirementDTO> list(RequirementListQry qry) {
        PageHelper.startPage(qry.getPageIndex(), qry.getPageSize());
        List<RequirementDO> list = requirementMapper.list(qry);
        PageInfo<RequirementDTO> page = new PageInfo(list);

        List<RequirementDTO> requirementDTOS = ObjectUtils.copyList(list, RequirementDTO.class);

        return  PageResponse.of(requirementDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public SingleResponse<AddResult> add(RequirementAddCmd cmd) {
        RequirementDO requirementDO = new RequirementDO();
        BeanUtils.copyProperties(cmd, requirementDO);
        requirementDO.setRequirementPhase("已立项");
        requirementDO.setCreateTime(new Date());
        requirementDO.setCreateBy(cmd.getLoginUserId());
        if (cmd.getIsReview())
            requirementDO.setRequirementStatus("草稿");
        else
            requirementDO.setRequirementStatus("已激活");
        requirementMapper.add(requirementDO);

        AddResult result = new AddResult();
        result.setId(requirementDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(RequirementUpdateCmd cmd) {
        RequirementDO requirementDO = new RequirementDO();
        BeanUtils.copyProperties(cmd, requirementDO);
        requirementMapper.update(requirementDO);

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<RequirementDTO> getById(@Param("id") Integer id) {
        RequirementDO requirementDO =  requirementMapper.getById(id);
        RequirementDTO requirementDTO = new RequirementDTO();
        BeanUtils.copyProperties(requirementDO, requirementDTO);

        return SingleResponse.of(requirementDTO);
    }

    @Override
    public Response review(RequirementReviewCmd cmd) {
        if (cmd.isOk())
            updateRequirementStatus(cmd.getId(), "已激活");
        else
            updateRequirementStatus(cmd.getId(), "草稿");
        return Response.buildSuccess();
    }

    @Override
    public Response close(OperateBaseCmd cmd) {
        updateRequirementStatus(cmd.getId(), "已关闭");
        return Response.buildSuccess();
    }

    private void updateRequirementStatus(Integer id, String requirementStatus) {
        requirementMapper.updateRequirementStatus(id, requirementStatus);
    }
}
