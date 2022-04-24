package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class RequirementListQry extends PageQuery {
    private Integer id;
    private Integer createBy;
    private String name;
    private String requirementStatus;
    private String requirementPhase;
    private Integer projectId;
    private Integer handleBy;
}
