package jxf.pms.dbo;

import lombok.Data;

@Data
public class TaskStatusStatDO {
    private Integer unstartCnt;
    private Integer doingCnt;
    private Integer finishCnt;
    private Integer pauseCnt;
    private Integer cancelCnt;
    private Integer closeCnt;
}
