package cn.sxl.utils.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * 封装 {@link HttpServletRequest}
 *
 * @author SxL
 * @since 1.0
 * Created on 12/12/2018 1:34 PM.
 */
public class HttpServletRequestUtils {
    public static int getInt(HttpServletRequest request, String key){
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key){
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static double getDouble(HttpServletRequest request, String key){
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        try {
            String result = request.getParameter(key);

            return result.trim();
        } catch (Exception e) {
            return null;
        }
    }
}
