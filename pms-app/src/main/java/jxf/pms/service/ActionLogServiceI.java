package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import jxf.pms.cmd.ActionLogListQry;
import jxf.pms.data.ActionLogDTO;
import jxf.pms.data.RoleDTO;

public interface ActionLogServiceI {
    PageResponse<ActionLogDTO> list(ActionLogListQry actionLogListQry);
}
