package jxf.pms.mapper;

import jxf.pms.cmd.ProjectListQry;
import jxf.pms.dbo.ProjectDO;
import jxf.pms.dbo.ProjectMemberAddDO;
import jxf.pms.dbo.UserBaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMemberMapper {

    List<UserBaseDO> getProjectMembers(Integer projectId);

    void addProjectMembers(ProjectMemberAddDO projectMemberAddDO);

    void delete(Integer projectId);
}
