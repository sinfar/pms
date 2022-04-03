package jxf.pms.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String loginName;
    private String name;
    private String email;
    private String phone_no;
    private List<Integer> roleIds;
    private List<String> roleNames;
    private Date lastLoginTime;
    private Date createTime;
    private String status;
}
