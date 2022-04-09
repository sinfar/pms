package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.PermissionListQry;
import jxf.pms.cmd.UserAddCmd;
import jxf.pms.cmd.UserListQry;
import jxf.pms.cmd.UserLoginCmd;
import jxf.pms.data.LoginResultDTO;
import jxf.pms.data.PermissionDTO;
import jxf.pms.data.UserDTO;

public interface UserServiceI {

    SingleResponse<LoginResultDTO> login(UserLoginCmd userLoginCmd);

    MultiResponse<PermissionDTO> getPermissionList(PermissionListQry qry);

    PageResponse<UserDTO> list(UserListQry userListQry);

    SingleResponse<String> add(UserAddCmd userAddCmd);

    SingleResponse<UserDTO> getById(Integer userId);
}
