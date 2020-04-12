package com.suollon.lvchang.nonbusiness.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象拷贝工具类
 * @author wangwl
 * @date 2020/4/12 17:23
 */
public class BeanCopierUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanCopierUtil.class);

    private BeanCopierUtil() {
    }

    private static Map<String, BeanCopier> beanCopierMap = new HashMap<>();

    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    public static <T> void mergeProperties(T target, T destination) {
        PropertyDescriptor[] propertyDescriptors = ReflectUtils.getBeanGetters(destination.getClass());
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            if (descriptor.getWriteMethod() != null) {
                try {
                    Object originalValue = descriptor.getReadMethod().invoke(target);
                    if (originalValue == null) {
                        Object defaultValue = descriptor.getReadMethod().invoke(destination);
                        descriptor.getWriteMethod().invoke(target, defaultValue);
                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    public static <T> T copyForClass(Object source, Class<T> clz) {
        T result;
        if (source == null) {
            return null;
        }
        try {
            result = clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("初始化失败e={}", e);
            throw new IllegalAccessError("无法初始化没有默认构函数的类");
        }

        copyProperties(source, result);

        return result;
    }

    /**
     * Pojo 类型转换（字段名/类型相同则被复制）
     *
     * @return
     */
    public static <E> List<E> copyForList(List source, Class<E> targetClass) {
        try {
            List result = source.getClass().newInstance();
            for (Object code : source) {
                result.add(copyForClass(code, targetClass));
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("对象转换失败,{}", e);
            throw new RuntimeException("对象转换失败" + source + "_" + targetClass);
        }
    }

    private static String generateKey(Class<?> source, Class<?> target) {
        return source.toString() + target.toString();
    }
}
