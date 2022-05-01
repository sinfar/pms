package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.BugDTO;
import jxf.pms.dbo.BugDO;
import jxf.pms.mapper.BugMapper;
import jxf.pms.service.BugService;
import jxf.pms.util.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class BugServiceImpl implements BugService {
    @Resource
    private BugMapper bugMapper;

    @Override
    public PageResponse<BugDTO> list(BugListQry qry) {
        PageHelper.startPage(qry.getPageIndex(), qry.getPageSize());
        List<BugDO> list = bugMapper.list(qry);
        PageInfo<BugDTO> page = new PageInfo(list);

        List<BugDTO> bugDTOS = new ArrayList<>();
        for(BugDO bugDO : list) {
            BugDTO bugDTO = new BugDTO();
            BeanUtils.copyProperties(bugDO, bugDTO);

            bugDTOS.add(bugDTO);
        }

        return  PageResponse.of(bugDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public SingleResponse<AddResult> add(BugAddCmd cmd) {
        BugDO bugDO = new BugDO();
        BeanUtils.copyProperties(cmd, bugDO);
        bugDO.setCreateTime(new Date());
        bugDO.setCreateBy(cmd.getLoginUserId());
        bugDO.setBugStatus("激活");
        bugMapper.add(bugDO);

        AddResult result = new AddResult();
        result.setId(bugDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(BugUpdateCmd cmd) {
        BugDO bugDO = new BugDO();
        BeanUtils.copyProperties(cmd, bugDO);
        bugMapper.update(bugDO);

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<BugDTO> getById(@Param("id") Integer id) {
        BugDO bugDO =  bugMapper.getById(id);
        BugDTO bugDTO = new BugDTO();
        BeanUtils.copyProperties(bugDO, bugDTO);

        return SingleResponse.of(bugDTO);
    }

    @Override
    public Response resolve(BugResolveCmd cmd) {
        bugMapper.resolve(cmd);
        return Response.buildSuccess();
    }

    @Override
    public Response close(OperateBaseCmd cmd) {
        updateBugStatus(cmd.getId(), "已关闭");
        return Response.buildSuccess();
    }

    @Override
    public Response active(OperateBaseCmd cmd) {
        updateBugStatus(cmd.getId(), "激活");
        return Response.buildSuccess();
    }

    @Override
    public List<BugDTO> all(Integer projectId) {
        List<BugDO> bugDOS = bugMapper.all(projectId);
        List<BugDTO> list = ObjectUtils.copyList(bugDOS, BugDTO.class);
        return list;
    }

    private void updateBugStatus(Integer id, String bugStatus) {
        bugMapper.updateBugStatus(id, bugStatus);
    }
}
