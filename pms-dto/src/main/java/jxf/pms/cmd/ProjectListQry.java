package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class ProjectListQry extends PageQuery {
    private Integer id;
    private String name;
    private String projectStatus;
    private String code;
}
