package jxf.pms.mapper;

import jxf.pms.cmd.ProjectListQry;
import jxf.pms.dbo.ProjectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<ProjectDO> list(ProjectListQry qry);

    void add(ProjectDO projectDO);

    void update(ProjectDO projectDO);

    ProjectDO getById(Integer id);

    void updateProjectStatus(@Param("id") Integer id, @Param("projectStatus") String projectStatus);
}
