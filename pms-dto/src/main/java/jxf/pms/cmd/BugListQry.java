package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class BugListQry extends PageQuery {
    private Integer id;
    private String name;
    private String bugType;
    private String bugStatus;
    private Integer projectId;
    private Integer handler;
    private Integer createBy;
    private Integer severityType;
}
