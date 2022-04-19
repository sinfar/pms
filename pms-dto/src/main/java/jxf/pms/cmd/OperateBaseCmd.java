package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class OperateBaseCmd extends  BaseCmd {
    @ActionLogObjectId
    private Integer id;
}
