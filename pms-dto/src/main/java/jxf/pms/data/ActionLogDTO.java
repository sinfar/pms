package jxf.pms.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActionLogDTO {
    private Integer operateBy;
    private String operateByName;
    private String operateObject;
    private String actionType;
    private String objectName;
    private Integer objectId;
    private Date createTime;
}
