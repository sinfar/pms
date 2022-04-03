package jxf.pms.dbo;

import lombok.Data;

@Data
public class LoginUserDO {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String mobile;
    private String status;
    private String phoneNo;
}
