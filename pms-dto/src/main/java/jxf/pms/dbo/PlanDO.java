package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class PlanDO {
    private Integer id;
    private String name;
    private Date beginDate;
    private Date endDate;
    private Integer bugs;
    private Integer requirements;
    private String planStatus;
    private String describe;
    private Date createTime;
    private Integer createBy;
}
