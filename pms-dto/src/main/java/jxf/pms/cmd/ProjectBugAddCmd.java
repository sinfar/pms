package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

import java.util.List;

@Data
public class ProjectBugAddCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    private List<Integer> bugIds;
}
