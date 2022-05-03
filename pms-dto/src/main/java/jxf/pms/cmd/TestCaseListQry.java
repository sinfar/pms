package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class TestCaseListQry extends PageQuery {
    private Integer id;
    private String name;
    private String testCaseType;
    private Integer projectId;
    private Integer requirementId;
    private String testCaseResult;
    private Integer createBy;
    private Integer testBy;
}
