package jxf.pms.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PlanDTO {
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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
