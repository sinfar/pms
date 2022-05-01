package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.BugDTO;

import java.util.List;

public interface BugService {
    PageResponse<BugDTO> list(BugListQry qry);

    SingleResponse<AddResult> add(BugAddCmd cmd);

    Response update(BugUpdateCmd cmd);

    SingleResponse<BugDTO> getById(Integer id);

    Response resolve(BugResolveCmd cmd);

    Response close(OperateBaseCmd cmd);

    Response active(OperateBaseCmd cmd);

    List<BugDTO> all(Integer projectId);
}
