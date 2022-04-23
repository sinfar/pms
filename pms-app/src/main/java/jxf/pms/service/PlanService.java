package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.AddResult;
import jxf.pms.data.PlanDTO;

import java.util.List;

public interface PlanService {
    PageResponse<PlanDTO> list(PlanListQry qry);

    List<PlanDTO> all();

    SingleResponse<AddResult> add(PlanAddCmd cmd);

    Response update(PlanUpdateCmd cmd);

    SingleResponse<PlanDTO> getById(Integer id);

    Response close(OperateBaseCmd cmd);

    Response review(PlanReviewCmd cmd);
}
