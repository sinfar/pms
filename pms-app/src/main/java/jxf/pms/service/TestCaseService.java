package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.TestCaseDTO;
import jxf.pms.data.TestCaseExecDTO;

import java.util.List;

public interface TestCaseService {
    PageResponse<TestCaseDTO> list(TestCaseListQry qry);

    SingleResponse<AddResult> add(TestCaseAddCmd cmd);

    Response update(TestCaseUpdateCmd cmd);

    SingleResponse<TestCaseDTO> getById(Integer id);

    Response addExec(TestCaseAddExecCmd cmd);

    List<TestCaseExecDTO> gettTestCaseExecs(Integer id);
}
