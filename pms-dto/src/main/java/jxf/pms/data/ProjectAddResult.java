package jxf.pms.data;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class ProjectAddResult {
    @ActionLogObjectId
    private Integer id;
}
