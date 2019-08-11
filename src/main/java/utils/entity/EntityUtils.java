package utils.entity;

import java.lang.reflect.Field;

/**
 * @ClaseName EntityUtils
 * @Description 针对实体类进行的一些工具操作
 * @Author Monster
 * @Date 2019/8/8 18:16
 * @Version 1.0
 **/
public class EntityUtils {

    /**
     * 根据实体对象和属性获取相应的值
     *
     * @param object 实体对象
     * @param field 属性
     * @return
     */
    public static Object getValueFromField(Object object, Field field) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getValueFromFieldName(Object object, String fieldName) {
        // 首先获取属性
        try {
            Field field = object.getClass().getField(fieldName);
            return EntityUtils.getValueFromField(object, field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
