package jxf.pms.dbo;

import lombok.Data;

@Data
public class PermissionDO {
    private  Integer id;
    private String name;
    private String code;
    private Integer level;
    private Integer parentId;
    private String url;
}
