package jxf.pms.interceptor;

import jxf.pms.domain.log.ActionType;
import jxf.pms.domain.log.OperateObject;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {
    OperateObject operateObject();
    ActionType actionType();
}
