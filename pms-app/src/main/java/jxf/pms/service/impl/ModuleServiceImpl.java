package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.*;
import jxf.pms.dbo.ModuleDO;
import jxf.pms.dbo.ProjectDO;
import jxf.pms.dbo.ProjectMemberAddDO;
import jxf.pms.dbo.UserBaseDO;
import jxf.pms.mapper.ModuleMapper;
import jxf.pms.mapper.ProjectMapper;
import jxf.pms.mapper.ProjectMemberMapper;
import jxf.pms.service.ModuleService;
import jxf.pms.service.ProjectService;
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
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;


    @Override
    public MultiResponse<ModuleDTO> list(ModuleListQry qry) {
        List<ModuleDO> doList = moduleMapper.list(qry);
        List<ModuleDTO> list = ObjectUtils.copyList(doList, ModuleDTO.class);
        return MultiResponse.of(list);
    }

    @Override
    public SingleResponse<ModuleAddResult> add(ModuleAddCmd cmd) {
        // 校验模块名重复
        ModuleDO other = moduleMapper.getByName(cmd.getName());
        if (other != null) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "名称重复");
        }

        ModuleDO module = new ModuleDO();
        module.setName(cmd.getName());
        module.setParentId(cmd.getParentId());
        module.setCreateBy(cmd.getLoginUserId());
        module.setCreateTime(new Date());
        moduleMapper.add(module);

        ModuleAddResult result = new ModuleAddResult();
        result.setId(module.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response rename(ModuleRenameCmd cmd) {
        // 校验模块名重复
        ModuleDO other = moduleMapper.getByName(cmd.getName());
        if (other != null && !other.getId().equals(cmd.getId())) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "名称重复");
        }

        moduleMapper.rename(cmd.getId(), cmd.getName());

        return Response.buildSuccess();
    }

    @Override
    public Response delete(ModuleDeleteCmd cmd) {
        moduleMapper.updateStatus(cmd.getId(), "删除");
        return Response.buildSuccess();
    }
}
