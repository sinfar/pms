package jxf.pms.data;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionDTO implements Serializable {
    private  Integer id;
    private String name;
    private String code;
    private Integer level;
    private Integer parentId;
    private Boolean isChecked;
}
