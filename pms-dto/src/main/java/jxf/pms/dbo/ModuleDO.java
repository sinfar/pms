package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class ModuleDO {
    private Integer Id;
    private String name;
    private Integer parentId;
    private String status;
    private Date createTime;
    private Integer createBy;
    private Integer projectId;

}
