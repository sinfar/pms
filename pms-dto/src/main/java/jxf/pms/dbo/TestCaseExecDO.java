package jxf.pms.dbo;


import lombok.Data;

import java.util.Date;

@Data
public class TestCaseExecDO {

  private Integer id;
  private String testResult;
  private String describe;
  private Integer createBy;
  private Date createTime;
  private Integer testCaseId;

  private String createByName;
}
