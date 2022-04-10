package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class ActionLogDO {

    private Integer operateBy;
    private String operateObject;
    private String actionType;
    private String objectName;
    private Integer objectId;
    private Date createTime;
    private Integer id;
    private String operateByName;

}
