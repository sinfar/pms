package jxf.pms.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ObjectUtils {
    public static <T> T copyObject(Object src, Class<T> objectClass) {
        T obj = null;
        try {
            obj = objectClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(src, obj);
        return obj;
    }

    public static <T> List<T> copyList(List<?> src, Class<T> objectClass) {
        List<T> list = new ArrayList<>();
        try {
            for (Object o : src){
                T obj = objectClass.newInstance();
                BeanUtils.copyProperties(o, obj);
                list.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
