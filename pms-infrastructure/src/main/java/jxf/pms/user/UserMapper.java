package jxf.pms.user;

import jxf.pms.cmd.UserListQry;
import jxf.pms.dbo.UserDO;
import jxf.pms.dbo.LoginUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    LoginUserDO getByLoginName(String loginName);

    List<UserDO> list(UserListQry userListQry);

    void add(UserDO userDO);

    UserDO getByPhoneNo(String phoneNo);

    UserDO getByName(String phoneNo);

    UserDO getById(Integer userId);
}
