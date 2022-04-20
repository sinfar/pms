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
import jxf.pms.mapper.ProjectMemberMapper;
import jxf.pms.service.ProjectService;
import jxf.pms.mapper.ProjectMapper;
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
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectMemberMapper projectMemberMapper;

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

        // 团队成员
        List<Integer> members = cmd.getMembers();
        if (!CollectionUtils.isEmpty(members)) {
            ProjectMemberAddDO projectMemberAddDO = new ProjectMemberAddDO();
            projectMemberAddDO.setMembers(members);
            projectMemberAddDO.setProjectId(projectDO.getId());
            projectMemberAddDO.setCreateBy(cmd.getLoginUserId());
            projectMemberAddDO.setCreateTime(new Date());
            projectMemberMapper.addProjectMembers(projectMemberAddDO);
        }

        ProjectAddResult result = new ProjectAddResult();
        result.setId(projectDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(ProjectUpdateCmd cmd) {
        ProjectDO projectDO = new ProjectDO();
        BeanUtils.copyProperties(cmd, projectDO);
        projectMapper.update(projectDO);

        // 项目成员
        projectMemberMapper.delete(cmd.getId());
        List<Integer> members = cmd.getMembers();
        if (!CollectionUtils.isEmpty(members)) {
            ProjectMemberAddDO projectMemberAddDO = new ProjectMemberAddDO();
            projectMemberAddDO.setMembers(members);
            projectMemberAddDO.setProjectId(projectDO.getId());
            projectMemberAddDO.setCreateBy(cmd.getLoginUserId());
            projectMemberAddDO.setCreateTime(new Date());
            projectMemberMapper.addProjectMembers(projectMemberAddDO);
        }

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<ProjectDTO> getById(@Param("id") Integer id) {
        ProjectDO projectDO =  projectMapper.getById(id);
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(projectDO, projectDTO);

        // 项目成员
        List<UserBaseDO> userBaseDOS = projectMemberMapper.getProjectMembers(id);
        List<UserBaseDTO> members = ObjectUtils.copyList(userBaseDOS, UserBaseDTO.class);
        projectDTO.setMembers(members);

        return SingleResponse.of(projectDTO);
    }

    @Override
    public Response start(OperateBaseCmd cmd) {
        updateProjectStatus(cmd.getId(), "进行中");
        return Response.buildSuccess();
    }

    @Override
    public Response block(OperateBaseCmd cmd) {
        updateProjectStatus(cmd.getId(), "挂起");
        return Response.buildSuccess();
    }

    @Override
    public Response close(OperateBaseCmd cmd) {
        updateProjectStatus(cmd.getId(), "已关闭");
        return Response.buildSuccess();
    }

    @Override
    public Response active(OperateBaseCmd cmd) {
        updateProjectStatus(cmd.getId(), "进行中");
        return Response.buildSuccess();
    }

    private void updateProjectStatus(Integer id, String projectStatus) {
        projectMapper.updateProjectStatus(id, projectStatus);
    }
}
