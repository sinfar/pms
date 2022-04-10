package jxf.pms.cmd;

import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateCmd extends  BaseCmd {
    @ActionLogObjectId
    private Integer id;
    @ActionLogObjectName
    private String name;
    private String loginName;
    private List<Integer> roleIds;
    private String phoneNo;
    private String email;
}
