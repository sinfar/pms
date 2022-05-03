package jxf.pms.dbo;


import lombok.Data;

import java.util.Date;

@Data
public class TestStepDO {

  private Integer id;
  private String name;
  private String expect;
  private Integer testCaseId;
}
