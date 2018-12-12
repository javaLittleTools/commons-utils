package cn.sxl.utils.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.servlet.http.HttpServletResponse;

/**
 * 生成二维码
 *
 * @author SxL
 * Created on 12/12/2018 1:46 PM.
 */
public class QrCodeUtils {
    /**
     * 生成二维码
     * @param content 传入数据
     * @param response 响应体
     * @return 二维码生成图
     */
    public static BitMatrix generateQRCode(String content, HttpServletResponse response, QrCodeArgs qrCodeArgs){
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        BitMatrix bitMatrix;

        try {
            // 编码内容，编码类型，宽度，高度，设置参数
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,
                    qrCodeArgs.getWidth(), qrCodeArgs.getHeight(), qrCodeArgs.getHints());
        } catch (WriterException e) {
            return null;
        }

        return bitMatrix;
    }
}
