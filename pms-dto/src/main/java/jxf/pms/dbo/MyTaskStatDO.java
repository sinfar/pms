package jxf.pms.dbo;

import lombok.Data;

@Data
public class MyTaskStatDO {
    private Integer taskCnt;
    private Integer requirementCnt;
    private Integer projectCnt;
    private Integer bugCnt;
}
