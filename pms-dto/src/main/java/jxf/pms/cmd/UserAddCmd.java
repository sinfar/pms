package jxf.pms.cmd;

import lombok.Data;

import java.util.List;

@Data
public class UserAddCmd {
    private String name;
    private String loginName;
    private String status;
    private List<Integer> roleIds;
    private String phoneNo;
}
