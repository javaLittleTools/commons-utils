package cn.sxl.utils.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

/**
 * BeanUtils
 *
 * @author SxL
 * @since 1.0
 * Created on 12/12/2018 2:37 PM.
 */
public class BeanUtils {
    /**
     * 复制两个对象的值
     * @param source 源对象
     * @param to 目标对象
     * @throws Exception 复制失败
     */
    public static void copyBean(Object source, Object to) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(to.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (PropertyDescriptor aSourceProperty : sourceProperty) {

                for (PropertyDescriptor aDestProperty : destProperty) {

                    if (aSourceProperty.getName().equals(aDestProperty.getName())) {
                        // 调用 source 的 getter 方法和 dest 的 setter 方法
                        aDestProperty.getWriteMethod()
                                .invoke(to, aSourceProperty.getReadMethod().invoke(source));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(" 属性复制失败:" + e.getMessage());
        }
    }

    /**
     * 将下划线命名的对象值复制到驼峰命名的对象
     * @param source 下划线命名的对象
     * @param to 驼峰命名的对象
     * @throws Exception 复制失败
     */
    public static void copyBeanToCamelNaming(Object source, Object to) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(to.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (PropertyDescriptor aSourceProperty : sourceProperty) {
                for (PropertyDescriptor aDestProperty : destProperty) {
                    if (underlineToCamel(aSourceProperty.getName()).equals(aDestProperty.getName())) {
                        // 调用 source 的 getter 方法和 dest 的 setter 方法
                        aDestProperty.getWriteMethod()
                                .invoke(to, aSourceProperty.getReadMethod().invoke(source));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(" 属性复制失败:" + e.getMessage());
        }
    }

    /**
     * 下划线转驼峰
     * @param para 下划线属性名
     * @return 驼峰属性名
     */
    public static String underlineToCamel(String para) {
        StringBuilder result = new StringBuilder();
        String[] a = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 驼峰转下划线
     * @param para 驼峰属性名
     * @return 下划线属性名
     */
    public static String camelToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        // 定位
        int temp = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 将下划线命名的 Bean 转成 驼峰命名的 Bean
     * @param sourceList
     * @param toList
     * @param className
     * @throws Exception
     */
    public static void batchConvertToCamel(List<Object> sourceList, List<Object> toList, String className) throws Exception {
        for (Object o : sourceList) {
            Object obj = Class.forName(className).newInstance();
            copyBeanToCamelNaming(o, obj);
            toList.add(obj);
        }
    }

    /**
     * JSON 格式的 toString 方法
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.JSON_STYLE);
    }
}
