package jxf.pms.dbo;

import lombok.Data;

@Data
public class RequirementStatusStatDO {
    private Integer draftCnt;
    private Integer activeCnt;
    private Integer closeCnt;
}
