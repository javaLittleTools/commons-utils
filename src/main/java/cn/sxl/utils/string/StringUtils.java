package cn.sxl.utils.string;
import java.text.MessageFormat;

/**
 * @author SxL
 * @since 1.4.0
 * 2019-03-15 17:52
 * 通过占位符进行字符串的拼接
 */
public class StringUtils {

    public static void println(String text, Object... replacement) {
        String printMsg = MessageFormat.format(text, replacement);

        System.out.println(printMsg);
    }

    public static String combine(String text, Object... replacement) {
        return MessageFormat.format(text, replacement);
    }
}
