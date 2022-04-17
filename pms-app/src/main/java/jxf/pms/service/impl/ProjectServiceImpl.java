package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.*;
import jxf.pms.dbo.*;
import jxf.pms.service.ProjectService;
import jxf.pms.mapper.ProjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageResponse<ProjectDTO> list(ProjectListQry qry) {
        PageHelper.startPage(qry.getPageIndex(), qry.getPageSize());
        List<ProjectDO> list = projectMapper.list(qry);
        PageInfo<ProjectDTO> page = new PageInfo(list);

        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for(ProjectDO projectDO : list) {
            ProjectDTO projectDTO = new ProjectDTO();
            BeanUtils.copyProperties(projectDO, projectDTO);
            if (projectDO.getEstimateHours() != null && projectDO.getEstimateHours() != 0)
                projectDTO.setRateOfProgress(projectDTO.getCostHours()/projectDO.getEstimateHours());
            projectDTOS.add(projectDTO);
        }

        return  PageResponse.of(projectDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public SingleResponse<ProjectAddResult> add(ProjectAddCmd cmd) {
        ProjectDO projectDO = new ProjectDO();
        BeanUtils.copyProperties(cmd, projectDO);
        projectDO.setCreateTime(new Date());
        projectDO.setCreateBy(cmd.getLoginUserId());
        projectDO.setProjectStatus("未开始");
        projectMapper.add(projectDO);

        ProjectAddResult result = new ProjectAddResult();
        result.setId(projectDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(ProjectUpdateCmd cmd) {
        ProjectDO projectDO = new ProjectDO();
        BeanUtils.copyProperties(cmd, projectDO);

        projectMapper.update(projectDO);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<ProjectDTO> getById(@Param("id") Integer id) {
        ProjectDO projectDO =  projectMapper.getById(id);
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(projectDO, projectDTO);
        return SingleResponse.of(projectDTO);
    }
}
