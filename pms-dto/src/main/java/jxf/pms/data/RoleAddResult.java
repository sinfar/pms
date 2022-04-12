package jxf.pms.data;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class RoleAddResult {
    @ActionLogObjectId
    private Integer id;
    private String password;
}
