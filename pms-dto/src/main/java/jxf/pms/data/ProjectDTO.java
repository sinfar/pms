package jxf.pms.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {
    private Integer Id;
    private String name;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    private Integer workingDays;
    private Integer leader;
    private String leaderName;
    private String projectStatus;
    private Integer estimateHours;
    private Integer costHours;
    private Integer leftHours;
    private Integer rateOfProgress;
    private String describe;

}
