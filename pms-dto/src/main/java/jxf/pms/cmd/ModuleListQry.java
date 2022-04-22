package jxf.pms.cmd;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

@Data
public class ModuleListQry extends BaseCmd {
    private Integer projectId;
}
