package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

import java.util.List;

@Data
public class UserDisabledCmd extends  BaseCmd {
    @ActionLogObjectId
    private Integer userId;
}
