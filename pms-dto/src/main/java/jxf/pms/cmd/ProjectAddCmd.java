package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectAddCmd extends BaseCmd {
    @ActionLogObjectName
    private String name;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    private Integer workingDays;
    private Integer leader;
    private String describe;
    private List<Integer> members;
}
