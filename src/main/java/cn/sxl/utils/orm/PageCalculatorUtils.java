package cn.sxl.utils.orm;

/**
 * 页数转成行数
 *
 * @author SxL
 * @since 1.0
 * Created on 12/12/2018 1:41 PM.
 */
public class PageCalculatorUtils {
    /**
     * 页数转换成行数
     * @param pageIndex 页数（第一页为 1）
     * @param pageSize 每页显示的数量
     * @return 行数
     */
    public static int calculatorRowIndex(int pageIndex, int pageSize){
        return (pageIndex > 1) ? (pageIndex - 1) * pageSize : 0;
    }
}
