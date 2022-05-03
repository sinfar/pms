package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.TestCaseDTO;
import jxf.pms.data.TestCaseExecDTO;
import jxf.pms.data.TestStepDTO;
import jxf.pms.dbo.TestCaseDO;
import jxf.pms.dbo.TestCaseExecDO;
import jxf.pms.dbo.TestStepDO;
import jxf.pms.mapper.TestCaseExecMapper;
import jxf.pms.mapper.TestCaseMapper;
import jxf.pms.mapper.TestStepMapper;
import jxf.pms.service.TestCaseService;
import jxf.pms.util.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@CatchAndLog
public class TestCaseServiceImpl implements TestCaseService {
    @Resource
    private TestCaseMapper testCaseMapper;
    @Resource
    private TestStepMapper testStepMapper;
    @Resource
    private TestCaseExecMapper testCaseExecMapper;

    @Override
    public PageResponse<TestCaseDTO> list(TestCaseListQry qry) {
        PageHelper.startPage(qry.getPageIndex(), qry.getPageSize());
        List<TestCaseDO> list = testCaseMapper.list(qry);
        PageInfo<TestCaseDTO> page = new PageInfo(list);

        List<TestCaseDTO> testCaseDTOS = new ArrayList<>();
        for(TestCaseDO testCaseDO : list) {
            TestCaseDTO testCaseDTO = new TestCaseDTO();
            BeanUtils.copyProperties(testCaseDO, testCaseDTO);

            testCaseDTOS.add(testCaseDTO);
        }

        return  PageResponse.of(testCaseDTOS, (int)page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    @Override
    public SingleResponse<AddResult> add(TestCaseAddCmd cmd) {
        // 添加测试用例
        TestCaseDO testCaseDO = new TestCaseDO();
        BeanUtils.copyProperties(cmd, testCaseDO);
        testCaseDO.setCreateTime(new Date());
        testCaseDO.setCreateBy(cmd.getLoginUserId());
        testCaseMapper.add(testCaseDO);

        // 添加测试步骤
        List<TestStepDO> steps = ObjectUtils.copyList(cmd.getTestSteps(), TestStepDO.class);
        testStepMapper.add(steps, testCaseDO.getId());

        AddResult result = new AddResult();
        result.setId(testCaseDO.getId());
        return SingleResponse.of(result);
    }

    @Override
    public Response update(TestCaseUpdateCmd cmd) {
        TestCaseDO testCaseDO = new TestCaseDO();
        BeanUtils.copyProperties(cmd, testCaseDO);
        testCaseMapper.update(testCaseDO);

        // 添加测试步骤
        testStepMapper.delete(cmd.getId());
        List<TestStepDO> steps = ObjectUtils.copyList(cmd.getTestSteps(), TestStepDO.class);
        testStepMapper.add(steps, testCaseDO.getId());

        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<TestCaseDTO> getById(@Param("id") Integer id) {
        TestCaseDO testCaseDO =  testCaseMapper.getById(id);
        TestCaseDTO testCaseDTO = new TestCaseDTO();
        BeanUtils.copyProperties(testCaseDO, testCaseDTO);

        // 查询测试步骤
        List<TestStepDO> testSteps = testStepMapper.list(id);
        List<TestStepDTO> testStepDTOS = ObjectUtils.copyList(testSteps, TestStepDTO.class);
        testCaseDTO.setTestSteps(testStepDTOS);

        return SingleResponse.of(testCaseDTO);
    }

    @Override
    public Response addExec(TestCaseAddExecCmd cmd) {
        Date now = new Date();

        // 添加执行记录
        TestCaseExecDO exec = ObjectUtils.copyObject(cmd, TestCaseExecDO.class);
        exec.setCreateBy(cmd.getLoginUserId());
        exec.setCreateTime(now);
        exec.setTestCaseId(cmd.getId());
        testCaseExecMapper.add(exec);

        // 更新测试用例状态
        testCaseMapper.updateTestCaseStatus(cmd.getId(), cmd.getTestResult(), cmd.getLoginUserId(), now);

        return Response.buildSuccess();
    }

    @Override
    public List<TestCaseExecDTO> gettTestCaseExecs(Integer id) {
        List<TestCaseExecDO> testCaseExecs = testCaseExecMapper.list(id);
       return ObjectUtils.copyList(testCaseExecs, TestCaseExecDTO.class);
    }
}
