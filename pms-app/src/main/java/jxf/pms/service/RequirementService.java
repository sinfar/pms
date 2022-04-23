package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.RequirementDTO;

public interface RequirementService {
    PageResponse<RequirementDTO> list(RequirementListQry qry);

    SingleResponse<AddResult> add(RequirementAddCmd cmd);

    Response update(RequirementUpdateCmd cmd);

    SingleResponse<RequirementDTO> getById(Integer id);

    Response close(OperateBaseCmd cmd);

    Response review(RequirementReviewCmd cmd);
}
