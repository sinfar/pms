package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.*;

import java.util.List;

public interface UserServiceI {

    SingleResponse<LoginResultDTO> login(UserLoginCmd userLoginCmd);

    MultiResponse<PermissionDTO> getPermissionList(PermissionListQry qry);

    PageResponse<UserDTO> list(UserListQry userListQry);

    SingleResponse<UserAddResult> add(UserAddCmd userAddCmd);

    SingleResponse<UserDTO> getById(Integer userId);

    Response update(UserUpdateCmd userUpdateCmd);

    Response disabled(UserDisabledCmd cmd);

    Response enabled(UserEnabledCmd cmd);

    Response delete(UserDeleteCmd cmd);

    Response resetPassword(UserResetPasswordCmd cmd);

    MultiResponse<UserBaseDTO> all();
}
