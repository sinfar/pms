package jxf.pms.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ModuleDTO {
    private Integer Id;
    private String name;
    private Integer parentId;

}
