package jxf.pms.data;


import lombok.Data;

import java.util.Date;

@Data
public class TestCaseExecDTO {

  private Integer id;
  private Integer testResult;
  private String describe;
  private Integer createBy;
  private Date createTime;
  private String testCaseId;

  private String createByName;
}
