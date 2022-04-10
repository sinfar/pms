package jxf.pms.data;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

@Data
public class UserAddResult {
    @ActionLogObjectId
    private Integer id;
    private String password;
}
