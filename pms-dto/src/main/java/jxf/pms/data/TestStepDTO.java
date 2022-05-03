package jxf.pms.data;


import lombok.Data;

@Data
public class TestStepDTO {

  private Integer id;
  private String name;
  private String expect;
  private Integer testCaseId;
}
