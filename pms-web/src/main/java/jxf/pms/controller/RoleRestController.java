package jxf.pms.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.*;
import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;
import jxf.pms.interceptor.ActionLog;
import jxf.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/org/role/list")
    public PageResponse<RoleDTO> list(RoleListQry roleListQry){
        return roleService.list(roleListQry);
    }

    @PostMapping("/org/role/permissions")
    public MultiResponse<PermissionDTO> permissions(@RequestBody RolePermissionsQry qry){
        return roleService.getAllPermissions(qry);
    }

    @PostMapping("/org/role/add")
    @ActionLog(operateObject= OperateObject.role,actionType = ActionType.create)
    public SingleResponse<RoleAddResult> add(@RequestBody RoleAddCmd cmd){
        return roleService.add(cmd);
    }

    @PostMapping("/org/role/update")
    @ActionLog(operateObject= OperateObject.role,actionType = ActionType.update)
    public Response update(@RequestBody RoleUpdateCmd cmd){
        return roleService.update(cmd);
    }

    @PostMapping("/org/role/info")
    public MultiResponse<RolePermissionDTO> rolePermissions(@RequestBody RolePermissionsQry qry){
        return roleService.rolePermissions(qry);
    }

    @PostMapping("/org/role/delete")
    @ActionLog(operateObject= OperateObject.role,actionType = ActionType.delete)
    public Response delete(@RequestBody RoleDeleteCmd cmd){
        return roleService.delete(cmd);
    }
}
