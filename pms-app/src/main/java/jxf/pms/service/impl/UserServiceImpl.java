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
import jxf.pms.domain.user.PasswordChecker;
import jxf.pms.service.UserServiceI;
import jxf.pms.service.impl.customer.executor.UserLoginCheckCmdExe;
import jxf.pms.domain.user.LoginToken;
import jxf.pms.user.PermissionMapper;
import jxf.pms.user.RoleMapper;
import jxf.pms.user.UserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public SingleResponse<UserAddResult> add(UserAddCmd userAddCmd) {
        // 校验手机号是否重复
        UserDO user = userMapper.getByPhoneNo(userAddCmd.getPhoneNo());
        if (user != null) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "手机号已存在");
        }

        // 校验登录名是否重复
        LoginUserDO loginUser = userMapper.getByLoginName(userAddCmd.getLoginName());
        if (loginUser != null) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "登录名已存在");
        }

        // 校验姓名是否重复
        UserDO user2 = userMapper.getByName(userAddCmd.getName());
        if (user2 != null) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "姓名已存在");
        }

        String password = PasswordChecker.randomPassword();
        String passwordEncrypt = PasswordChecker.encrypt(password);

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userAddCmd, userDO);
        userDO.setCreateTime(new Date());
        userDO.setCreateBy(userAddCmd.getLoginUserId());
        userDO.setPassword(passwordEncrypt);
        userMapper.add(userDO);

        UserAddResult result = new UserAddResult();
        result.setId(userDO.getId());
        result.setPassword(password);
        return  SingleResponse.of(result);
    }

    @Override
    public SingleResponse<UserDTO> getById(Integer userId) {
        UserDO user = userMapper.getById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setRoleIds(user.getRoles());
        return SingleResponse.of(userDTO);
    }

    @Override
    public Response update(UserUpdateCmd userUpdateCmd) {
        // 校验手机号是否重复
        UserDO user = userMapper.getByPhoneNo(userUpdateCmd.getPhoneNo());
        if (user != null && !user.getId().equals(userUpdateCmd.getId())) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "手机号已存在");
        }

        // 校验登录名是否重复
        LoginUserDO loginUser = userMapper.getByLoginName(userUpdateCmd.getLoginName());
        if (loginUser != null && !loginUser.getId().equals(userUpdateCmd.getId())) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "登录名已存在");
        }

        // 校验姓名是否重复
        UserDO user2 = userMapper.getByName(userUpdateCmd.getName());
        if (user2 != null && !user2.getId().equals(userUpdateCmd.getId())) {
            return SingleResponse.buildFailure(ErrorCode.b_error.getErrCode(), "姓名已存在");
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userUpdateCmd, userDO);
        userMapper.update(userDO);

        return Response.buildSuccess();
    }

    @Override
    public Response disabled(UserDisabledCmd cmd) {
        userMapper.updateStatus(cmd.getUserId(), "禁用");
        return Response.buildSuccess();
    }

    @Override
    public Response enabled(UserEnabledCmd cmd) {
        userMapper.updateStatus(cmd.getUserId(), "正常");
        return Response.buildSuccess();
    }

    @Override
    public Response delete(UserDeleteCmd cmd) {
        userMapper.updateStatus(cmd.getUserId(), "删除");
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<String> resetPassword(UserResetPasswordCmd cmd) {
        String password = PasswordChecker.randomPassword();
        String passwordEncrypt = PasswordChecker.encrypt(password);

        userMapper.updatePassword(cmd.getUserId(), passwordEncrypt);

        return SingleResponse.of(password);
    }

    @Override
    public MultiResponse<UserBaseDTO> all() {
        List<UserBaseDO> userBaseDOS = userMapper.all();
        List<UserBaseDTO> userBaseDTOList = new ArrayList<>();
        for (UserBaseDO o : userBaseDOS) {
            UserBaseDTO t = new UserBaseDTO();
            BeanUtils.copyProperties(o, t);
            userBaseDTOList.add(t);
        }
        return MultiResponse.of(userBaseDTOList);
    }
}
