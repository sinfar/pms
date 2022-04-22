package jxf.pms.mapper;

import jxf.pms.cmd.ModuleListQry;
import jxf.pms.dbo.ModuleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModuleMapper {

    List<ModuleDO> list(ModuleListQry qry);

    void add(ModuleDO moduleDO);

    void rename(@Param("id") Integer id, @Param("name") String name);

    void updateStatus(@Param("id") Integer id, @Param("status") String status);

    ModuleDO getByName(String name);
}
