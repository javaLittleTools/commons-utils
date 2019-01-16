package cn.sxl.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件属性
 *
 * @author SxL
 * @since 1.1.0
 * Created on 12/26/2018 4:01 PM.
 */
public class PropertyUtils {
    private static Properties props;

    synchronized static private void loadProps(String propertiesPath) {
        props = new Properties();
        InputStream in = null;
        try {

            in = PropertyUtils.class.getClassLoader().
                    getResourceAsStream(propertiesPath);
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String propertyPath, String key) {
        if (null == props || props.size() == 0) {
            loadProps(propertyPath);
        }
        return props.getProperty(key);
    }
}
