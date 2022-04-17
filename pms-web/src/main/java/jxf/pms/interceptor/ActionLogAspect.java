package jxf.pms.interceptor;

import com.alibaba.cola.dto.SingleResponse;
import jxf.pms.annotation.ActionLogObjectId;
import jxf.pms.annotation.ActionLogObjectName;
import jxf.pms.cmd.BaseCmd;
import jxf.pms.dbo.ActionLogDO;
import jxf.pms.mapper.ActionLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class ActionLogAspect {
    @Resource
    private ActionLogMapper actionLogMapper;

    @Pointcut("@annotation(jxf.pms.interceptor.ActionLog)")
    public void logPointCut(){

    }

    //处理完请求后执行
    @AfterReturning(pointcut = "logPointCut()",returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) throws IllegalAccessException {
        Integer objectId = null;
        String objectName = null;
        String operateObject = null;
        String actionType = null;
        Integer operateBy = null;

        ActionLog actionLog = getAnnotation(joinPoint);
        if (actionLog == null) return;
        operateObject = actionLog.operateObject().name;
        actionType = actionLog.actionType().name;

        Object arg = joinPoint.getArgs()[0];

        // 登录用户ID
        if (arg instanceof BaseCmd) {
            operateBy = ((BaseCmd) arg).getLoginUserId();
        }

        // 响应中获取对象ID
        if (objectId == null) {
            if (jsonResult instanceof SingleResponse) {
                SingleResponse resp = (SingleResponse) jsonResult;
                // 没有成功直接返回
                if (!resp.isSuccess()) return;
                Field[] fields2 = resp.getData().getClass().getDeclaredFields();
                for (Field f : fields2) {
                    ActionLogObjectId actionLogObjectId = f.getAnnotation(ActionLogObjectId.class);
                    if (actionLogObjectId != null) {
                        f.setAccessible(true);
                        Object value = f.get(resp.getData());
                        objectId = (Integer) value;
                    }
                }
            }
        }

        // 请求中获取对象名称和对象ID
        Field[] requestFields = arg.getClass().getDeclaredFields();
        for (Field f : requestFields) {
            ActionLogObjectId actionLogObjectId = f.getAnnotation(ActionLogObjectId.class);
            if (actionLogObjectId != null && objectId == null) {
                f.setAccessible(true);
                Object value = f.get(arg);
                objectId = (Integer) value;
            }

            ActionLogObjectName ActionLogObjectName = f.getAnnotation(ActionLogObjectName.class);
            if (ActionLogObjectName != null && objectName == null) {
                f.setAccessible(true);
                Object value = f.get(arg);
                objectName = (String) value;
            }
        }

        // 对象名称没找到，从数据库中查
        if (objectName == null && objectId != null) {
            objectName = actionLogMapper.getObjectNameByObjectId(operateObject, objectId);
        }

        // 保存记录
        ActionLogDO log = new ActionLogDO();
        log.setActionType(actionType);
        log.setObjectId(objectId);
        log.setObjectName(objectName);
        log.setOperateObject(operateObject);
        log.setOperateBy(operateBy);
        log.setCreateTime(new Date());
        actionLogMapper.add(log);
    }



    //获取注解
    public ActionLog getAnnotation(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method != null){
            return method.getAnnotation(ActionLog.class);
        }
        return null;
    }




}
