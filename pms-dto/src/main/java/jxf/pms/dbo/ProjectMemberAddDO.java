package jxf.pms.dbo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectMemberAddDO {
    private Integer projectId;
    private List<Integer> members;
    private Integer createBy;
    private Date createTime;

}
