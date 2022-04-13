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
import jxf.pms.dbo.*;
import jxf.pms.service.RoleServiceI;
import jxf.pms.user.PermissionMapper;
import jxf.pms.user.RoleMapper;
import jxf.pms.user.RolePermissionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
@CatchAndLog
public class RoleServiceImpl implements RoleServiceI {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public PageResponse<RoleDTO> list(RoleListQry roleListQry) {
        PageHelper.startPage(roleListQry.getPageIndex(), roleListQry.getPageSize());
        List<RoleDO> list = roleMapper.list();
        PageInfo<RoleDO> page = new PageInfo(list);

        List<RoleDTO> roleDTOS = new ArrayList<>();
        for(RoleDO roleDO : list) {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(roleDO, roleDTO);
            roleDTOS.add(roleDTO);
        }

        return  PageResponse.of(roleDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public MultiResponse<RoleBaseDTO> all() {
        List<RoleBaseDO> list = roleMapper.all();
        List<RoleBaseDTO> roleDTOS = new ArrayList<>();
        for(RoleBaseDO roleDO : list) {
            RoleBaseDTO roleDTO = new RoleBaseDTO();
            BeanUtils.copyProperties(roleDO, roleDTO);
            roleDTOS.add(roleDTO);
        }
        return MultiResponse.of(roleDTOS);
    }

    @Override
    public MultiResponse<PermissionDTO> getAllPermissions(RolePermissionsQry qry) {
        List<PermissionDO> permissionDTOS = permissionMapper.all();
        List<PermissionDTO> list =  new ArrayList<>();
        for(PermissionDO permissionDO : permissionDTOS) {
            PermissionDTO permissionDTO  = new PermissionDTO();
            BeanUtils.copyProperties(permissionDO, permissionDTO);
            list.add(permissionDTO);
        }

        // 查询职位权限
        if (qry.getRoleId() != null) {
            List<RolePermissionDO> rolePermissionDOS = rolePermissionMapper.getByRoleId(qry.getRoleId());
            Set<Integer> permissionIds = rolePermissionDOS.stream().map(RolePermissionDO::getPermissionId).collect(Collectors.toSet());
            for (PermissionDTO dto : list) {
                dto.setIsChecked(permissionIds.contains(dto.getId()));
            }
        }
        return MultiResponse.of(list);
    }

    @Override
    public MultiResponse<RolePermissionDTO> rolePermissions(RolePermissionsQry qry) {
        List<RolePermissionDO> rolePermissionDOS = rolePermissionMapper.getByRoleId(qry.getRoleId());
        List<RolePermissionDTO> list =  new ArrayList<>();
        for(RolePermissionDO rolePermissionDO : rolePermissionDOS) {
            RolePermissionDTO rolePermissionDTO  = new RolePermissionDTO();
            BeanUtils.copyProperties(rolePermissionDO, rolePermissionDTO);
            list.add(rolePermissionDTO);
        }
        return MultiResponse.of(list);
    }

    @Override
    public Response delete(RoleDeleteCmd cmd) {
        roleMapper.updateStatus(cmd.getRoleId(), "删除");
        return Response.buildSuccess();
    }

    @Override
    public Response update(RoleUpdateCmd cmd) {
        // 校验名字
        RoleBaseDO ro = roleMapper.getByName(cmd.getName());
        if (ro != null && !ro.getId().equals(cmd.getRoleId())) {
            return Response.buildFailure(ErrorCode.b_error.getErrCode(), "名字重复");
        }

        // 修改角色
        RoleDO roleDO = new RoleDO();
        roleDO.setId(cmd.getRoleId());
        roleDO.setName(cmd.getName());
        roleMapper.update(roleDO);

        // 删除原角色权限
        rolePermissionMapper.deleteByRoleId(cmd.getRoleId());

        // 添加角色权限
        RolePermissionAddDO rolePermissionAddDO = new RolePermissionAddDO();
        rolePermissionAddDO.setRoleId(roleDO.getId());
        rolePermissionAddDO.setCreateBy(cmd.getLoginUserId());
        rolePermissionAddDO.setCreateTime(new Date());
        rolePermissionAddDO.setPermissionIds(cmd.getPermissionIds());
        rolePermissionMapper.add(rolePermissionAddDO);

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<RoleBaseDTO> info(Integer roleId) {
        RoleBaseDO roleBaseDO = roleMapper.info(roleId);
        RoleBaseDTO roleDTO = new RoleBaseDTO();
        BeanUtils.copyProperties(roleBaseDO, roleDTO);
        return SingleResponse.of(roleDTO);
    }

    @Transactional
    @Override
    public SingleResponse<RoleAddResult> add(RoleAddCmd cmd) {
        // 校验名字
        RoleBaseDO ro = roleMapper.getByName(cmd.getName());
        if (ro != null) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "名字重复");
        }

        // 添加角色
        RoleDO roleDO = new RoleDO();
        roleDO.setName(cmd.getName());
        roleDO.setCreateBy(cmd.getLoginUserId());
        roleDO.setCreateTime(new Date());
        roleMapper.add(roleDO);

        // 添加角色权限
        RolePermissionAddDO rolePermissionAddDO = new RolePermissionAddDO();
        rolePermissionAddDO.setRoleId(roleDO.getId());
        rolePermissionAddDO.setCreateBy(cmd.getLoginUserId());
        rolePermissionAddDO.setCreateTime(new Date());
        rolePermissionAddDO.setPermissionIds(cmd.getPermissionIds());
        rolePermissionMapper.add(rolePermissionAddDO);

        RoleAddResult result = new RoleAddResult();
        result.setId(roleDO.getId());
        return SingleResponse.of(result);
    }
}
