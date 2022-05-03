package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import jxf.pms.data.TestStepDTO;
import lombok.Data;

import java.util.List;

@Data
public class TestCaseAddExecCmd extends BaseCmd {
    @ActionLogObjectId
    private Integer id;
    private String testResult;
    private String describe;
}
