package jxf.pms.cmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import jxf.pms.annotation.ActionLogObjectName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ModuleAddCmd extends BaseCmd {
    @ActionLogObjectName
    private String name;
    private Integer parentId;
}
