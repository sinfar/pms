package jxf.pms.mapper;

import jxf.pms.cmd.TestCaseListQry;
import jxf.pms.dbo.TestCaseDO;
import jxf.pms.dbo.TestCaseExecDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestCaseExecMapper {

    List<TestCaseExecDO> list(Integer testCaseId);

    void add(TestCaseExecDO testCaseExecDO);

}
