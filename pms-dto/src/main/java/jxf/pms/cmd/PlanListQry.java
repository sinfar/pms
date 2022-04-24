package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class PlanListQry extends PageQuery {
    private Integer id;
    private Integer createBy;
    private String name;
    private String planStatus;
    private Integer projectId;
}
