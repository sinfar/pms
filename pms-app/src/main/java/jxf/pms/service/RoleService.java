package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.*;

import java.awt.color.ICC_Profile;
import java.util.List;

public interface RoleService {
    PageResponse<RoleDTO> list(RoleListQry roleListQry);

    MultiResponse<RoleBaseDTO> all();

    MultiResponse<PermissionDTO> getAllPermissions(RolePermissionsQry qry);

    SingleResponse<RoleAddResult> add(RoleAddCmd cmd);

    SingleResponse<RoleBaseDTO> info(Integer roleId);

    MultiResponse<RolePermissionDTO> rolePermissions(RolePermissionsQry qry);

    Response delete(RoleDeleteCmd cmd);

    Response update(RoleUpdateCmd cmd);
}
