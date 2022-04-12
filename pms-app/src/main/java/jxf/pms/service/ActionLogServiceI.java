package jxf.pms.service;

import com.alibaba.cola.dto.PageResponse;
import jxf.pms.cmd.ActionLogListQry;
import jxf.pms.data.ActionLogDTO;

public interface ActionLogServiceI {
    PageResponse<ActionLogDTO> list(ActionLogListQry actionLogListQry);
}
