package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import jxf.pms.data.RoleDTO;

public interface RoleServiceI {
    MultiResponse<RoleDTO> list();
}
