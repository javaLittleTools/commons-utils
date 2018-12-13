package cn.sxl.utils.qrcode;

import com.google.zxing.EncodeHintType;
import lombok.Data;

import java.util.Map;

/**
 * 二维码生成 {@link QrCodeUtils} 配置参数
 *
 * @author SxL
 * @since 1.0
 * Created on 12/12/2018 1:50 PM.
 */
@Data
public class QrCodeArgs {
    private int height;

    private int width;

    Map<EncodeHintType, Object> hints;
}
