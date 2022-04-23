package jxf.pms.data;

import jxf.pms.annotation.ActionLogObjectId;
import lombok.Data;

@Data
public class AddResult {
    @ActionLogObjectId
    private Integer id;
}
