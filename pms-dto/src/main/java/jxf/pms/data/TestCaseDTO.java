package jxf.pms.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TestCaseDTO {
    private Integer id;
    private String name;
    private String precondition;
    private Integer moduleId;
    private Integer projectId;
    private String testPhase;
    private Integer requirementId;
    private Integer testBy;
    private Date testTime;
    private Integer createBy;
    private Date createTime;
    private String testCaseType;
    private String testCaseResult;

    private String moduleName;
    private String projectName;
    private String requirementName;
    private String testByName;
    private String createByName;
    private List<TestStepDTO> testSteps;

    private Integer execCnt;

}
