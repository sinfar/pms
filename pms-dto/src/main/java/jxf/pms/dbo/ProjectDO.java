package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDO {
    private Integer Id;
    private String name;
    private String code;
    private Date beginDate;
    private Date endDate;
    private Integer workingDays;
    private Integer leader;
    private String leaderName;
    private String projectStatus;
    private Integer estimateHours;
    private Integer costHours;
    private Integer leftHours;
    private Date createTime;
    private Integer createBy;
    private String describe;
}
