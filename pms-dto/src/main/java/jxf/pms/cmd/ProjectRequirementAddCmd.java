package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectRequirementAddCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    private List<Integer> requirementIds;
}
