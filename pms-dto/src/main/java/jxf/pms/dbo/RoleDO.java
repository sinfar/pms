package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDO {
    private Integer Id;
    private String name;
    private String status;
    private String createByName;
    private Date createTime;
    private Integer createBy;
}
