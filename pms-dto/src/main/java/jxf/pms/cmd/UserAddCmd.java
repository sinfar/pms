package jxf.pms.cmd;

import lombok.Data;

import java.util.List;

@Data
public class UserAddCmd extends  BaseCmd {
    private String name;
    private String loginName;
    private List<Integer> roleIds;
    private String phoneNo;
    private String email;
}
