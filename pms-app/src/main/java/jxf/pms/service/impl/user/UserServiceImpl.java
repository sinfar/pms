package jxf.pms.service.impl.user;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.UserLoginCmd;
import jxf.pms.cmd.PermissionListQry;
import jxf.pms.cmd.UserListQry;
import jxf.pms.data.LoginResultDTO;
import jxf.pms.data.LoginUserDTO;
import jxf.pms.data.PermissionDTO;
import jxf.pms.data.UserDTO;
import jxf.pms.dbo.LoginUserDO;
import jxf.pms.dbo.PermissionDO;
import jxf.pms.dbo.RoleDO;
import jxf.pms.dbo.UserDO;
import jxf.pms.service.UserServiceI;
import jxf.pms.service.impl.customer.executor.UserLoginCheckCmdExe;
import jxf.pms.domain.user.LoginToken;
import jxf.pms.user.PermissionMapper;
import jxf.pms.user.RoleMapper;
import jxf.pms.user.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
@CatchAndLog
public class UserServiceImpl implements UserServiceI {

    @Resource
    private UserLoginCheckCmdExe userLoginCheckCmdExe;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public SingleResponse<LoginResultDTO> login(UserLoginCmd userLoginCmd) {
        // 登录校验用户名密码
        SingleResponse<LoginUserDO> response = userLoginCheckCmdExe.execute(userLoginCmd);
        // 校验失败直接返回
        if (!response.isSuccess())
            return SingleResponse.buildFailure(response.getErrCode(), response.getErrMessage());

        // 校验通过发放凭证
        LoginUserDO user = response.getData();
        String token = LoginToken.geToken(userLoginCmd.getLoginName(), System.currentTimeMillis());
        LoginUserDTO loginUser  = new LoginUserDTO(user.getId(), user.getName());
        LoginResultDTO result = new LoginResultDTO(loginUser, token);
        result.setToken(token);

        return SingleResponse.of(result);
    }

    @Override
    public MultiResponse<PermissionDTO> getPermissionList(PermissionListQry qry) {
        List<PermissionDO>  permissions = permissionMapper.getPermissionList(qry.getUserId());
        List<PermissionDTO> permissionDTOS = new ArrayList<>(permissions.size());
        for (PermissionDO o: permissions) {
            PermissionDTO d = new PermissionDTO();
            d.setId(o.getId());
            d.setCode(o.getCode());
            d.setName(o.getName());
            d.setLevel(o.getLevel());
            d.setParentId(o.getParentId());
            permissionDTOS.add(d);
        }
        return MultiResponse.of(permissionDTOS);

    }

    @Override
    public PageResponse<UserDTO> list(UserListQry userListQry) {
        List<RoleDO> roleList = roleMapper.list();

        PageHelper.startPage(userListQry.getPageIndex(), userListQry.getPageSize());
        List<UserDO> userList = userMapper.list(userListQry);
        PageInfo<UserDO> page = new PageInfo(userList);

        List<UserDTO> userDTOS = new ArrayList<>();
        for(UserDO user: userList) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setRoleIds(user.getRoles());

            // 设置角色名称
            if (!CollectionUtils.isEmpty(user.getRoles())) {
                List<String> roleNames = new ArrayList<>();
                for(Integer role: user.getRoles()) {
                    RoleDO r =  roleList.stream().filter(t->t.getId().equals(role)).findFirst().get();
                    roleNames.add(r.getName());
                }
                userDTO.setRoleNames(roleNames);
            }
            userDTOS.add(userDTO);
        }

        return  PageResponse.of(userDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }
}
