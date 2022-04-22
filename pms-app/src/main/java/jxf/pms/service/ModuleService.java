package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.cmd.*;
import jxf.pms.data.ModuleDTO;
import jxf.pms.data.ModuleAddResult;

public interface ModuleService {
    MultiResponse<ModuleDTO> list(ModuleListQry qry);

    SingleResponse<ModuleAddResult> add(ModuleAddCmd cmd);

    Response rename(ModuleRenameCmd cmd);

    Response delete(ModuleDeleteCmd cmd);
}
