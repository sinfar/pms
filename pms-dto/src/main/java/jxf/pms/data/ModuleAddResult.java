package jxf.pms.data;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class ModuleAddResult {
    @ActionLogObjectId
    private Integer id;
}
