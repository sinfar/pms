package jxf.pms.data;

import lombok.Data;

import java.util.Date;

@Data
public class PlanDTO {
    private Integer id;
    private String name;
    private Date beginDate;
    private Date endDate;
    private Integer bugCnt;
    private Integer requirementCnt;
    private String describe;
    private Date createTime;
    private String planStatus;
    private Integer createBy;
    private String createByName;
    private Integer projectId;
    private String projectName;

}
