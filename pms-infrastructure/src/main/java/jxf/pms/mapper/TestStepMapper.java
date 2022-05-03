package jxf.pms.mapper;

import jxf.pms.dbo.TestCaseExecDO;
import jxf.pms.dbo.TestStepDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestStepMapper {

    List<TestStepDO> list(Integer testCaseId);

    void add(@Param("testSteps") List<TestStepDO> testSteps, @Param("testCaseId") Integer testCaseId);

    void delete(Integer testCaseId);
}
