package jxf.pms.cmd;

import jxf.pms.data.UserThreadLocal;
import lombok.Data;

@Data
public class BaseCmd {
    private Integer loginUserId;

    public Integer getLoginUserId() {
        return UserThreadLocal.get().getId();
    }
}
