package jxf.pms.data;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {
    private Integer Id;
    private String name;
    private String status;
    private String createByName;
    private Date createTime;
}
