package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class UserListQry extends PageQuery {
    private Integer userId;
    private String name;
    private String loginName;
    private String status;
    private Integer roleId;
}
