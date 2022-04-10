package jxf.pms.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import jxf.pms.data.RoleDTO;
import jxf.pms.dbo.RoleDO;
import jxf.pms.service.RoleServiceI;
import jxf.pms.user.RoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
@CatchAndLog
public class RoleServiceImpl implements RoleServiceI {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public MultiResponse<RoleDTO> list() {
        List<RoleDO> roles = roleMapper.list();
        List<RoleDTO> roleList = new ArrayList<>();
        for (RoleDO r : roles) {
            RoleDTO ro = new RoleDTO();
            BeanUtils.copyProperties(r, ro);
            roleList.add(ro);
        }
        return MultiResponse.of(roleList);
    }
}
