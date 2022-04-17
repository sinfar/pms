package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.*;

public interface ProjectService {
    PageResponse<ProjectDTO> list(ProjectListQry qry);

    SingleResponse<ProjectAddResult> add(ProjectAddCmd cmd);

    Response update(ProjectUpdateCmd cmd);

    SingleResponse<ProjectDTO> getById(Integer id);
}
