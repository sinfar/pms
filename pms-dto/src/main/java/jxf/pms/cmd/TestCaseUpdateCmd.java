package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import jxf.pms.data.TestStepDTO;
import lombok.Data;

import java.util.List;

@Data
public class TestCaseUpdateCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    @ActionLogObjectName
    private String name;
    private Integer projectId;
    private Integer requirementId;
    private Integer moduleId;
    private String precondition;
    private String testPhase;
    private String testCaseType;
    private List<TestStepDTO> testSteps;
}
