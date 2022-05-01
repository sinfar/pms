package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

@Data
public class BugResolveCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    private String solutionType;
}
