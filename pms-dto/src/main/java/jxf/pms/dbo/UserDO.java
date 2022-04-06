package jxf.pms.dbo;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDO {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String mobile;
    private String status;
    private String phoneNo;
    private Date lastLoginTime;
    private String loginName;
    private Integer createBy;
    private Date createTime;
    private List<Integer> roles;

    public void setRoles(String rolesStr) {
        if (rolesStr == null)
            return;
        String array = rolesStr.substring(1, rolesStr.length() -1);
        String[] strArray = array.split(",");
        roles = new ArrayList<>();
        for (String s : strArray) {
            roles.add(Integer.parseInt(s.replaceAll("\\\"", "")));
        }
    }
}
