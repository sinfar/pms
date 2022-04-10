package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class ActionLogListQry extends PageQuery {
    private Integer operateBy;
    private String operateObject;
    private String actionType;
    private String objectName;
    private Integer objectId;
}
