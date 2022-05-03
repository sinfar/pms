package jxf.pms.dbo;


import lombok.Data;

import java.util.Date;

@Data
public class TestCaseDO {

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

  private Integer execCnt;
}
