package jxf.pms.dbo;

import lombok.Data;

@Data
public class ProjectStatDO {
    private Integer id;
    private String name;
    private Integer activeReqCnt;
    private Integer draftReqCnt;
    private Integer unexpiredPlanCnt;
    private Integer unstartTaskCnt;
    private Integer doingTaskCnt;
    private Integer activeBugCnt;
    private Integer uncloseBugCnt;
}
