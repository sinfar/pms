package jxf.pms.dbo;

import lombok.Data;

@Data
public class BugStatusStatDO {
    private Integer activeCnt;
    private Integer fixedCnt;
    private Integer closeCnt;
}
