package jxf.pms.mapper;

import jxf.pms.cmd.TestCaseListQry;
import jxf.pms.dbo.TestCaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TestCaseMapper {

    List<TestCaseDO> list(TestCaseListQry qry);

    void add(TestCaseDO testCaseDO);

    void update(TestCaseDO testCaseDO);

    TestCaseDO getById(Integer id);

    void updateTestCaseStatus(@Param("id") Integer id,
                              @Param("testCaseResult") String testCaseResult,
                              @Param("testBy") Integer testBy,
                              @Param("testTime")Date testTime);

}
