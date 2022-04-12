package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.ActionLogListQry;
import jxf.pms.cmd.RoleAddCmd;
import jxf.pms.cmd.RoleListQry;
import jxf.pms.cmd.RolePermissionsQry;
import jxf.pms.data.*;

import java.awt.color.ICC_Profile;
import java.util.List;

public interface RoleServiceI {
    PageResponse<RoleDTO> list(RoleListQry roleListQry);

    MultiResponse<RoleBaseDTO> all();

    MultiResponse<PermissionDTO> getAllPermissions();

    SingleResponse<RoleAddResult> add(RoleAddCmd cmd);

    SingleResponse<RoleBaseDTO> info(Integer roleId);

    MultiResponse<RolePermissionDTO> rolePermissions(RolePermissionsQry qry);
}
