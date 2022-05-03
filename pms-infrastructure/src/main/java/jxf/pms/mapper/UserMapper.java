package jxf.pms.mapper;

import jxf.pms.cmd.UserListQry;
import jxf.pms.dbo.MyTaskStatDO;
import jxf.pms.dbo.UserBaseDO;
import jxf.pms.dbo.UserDO;
import jxf.pms.dbo.LoginUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    LoginUserDO getByLoginName(String loginName);

    List<UserDO> list(UserListQry userListQry);

    void add(UserDO userDO);

    UserDO getByPhoneNo(String phoneNo);

    UserDO getByName(String phoneNo);

    UserDO getById(Integer userId);

    void update(UserDO userDO);

    void updateStatus(@Param("id") Integer userId, @Param("status") String status);

    void updatePassword(@Param("id") Integer userId, @Param("password") String password);

    List<UserBaseDO> all();

    MyTaskStatDO getMyTaskStat(Integer userId);
}
