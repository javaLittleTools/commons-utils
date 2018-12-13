package cn.sxl.utils.address;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 使用度短链接的接口，将其转成短的URL
 *
 * @author SxL
 * @since 1.0
 * Created on 12/12/2018 2:12 PM.
 */
public class ShortNetAddressUtils {
    public static int TIMEOUT = 30 * 1000;
    public static String ENCODING = "UTF-8";

    /**
     * 根据传入的url，通过访问百度短链接的接口， 将其转换成短的URL
     *
     * @param originURL
     * @return
     */
    public static String getShortURL(String originURL) {
        String tinyUrl = null;
        try {
            // 指定百度短链接的接口
            URL url = new URL("http://dwz.cn/create.php");
            // 建立连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接的参数
            // 使用连接进行输出
            connection.setDoOutput(true);
            // 使用连接进行输入
            connection.setDoInput(true);
            // 不使用缓存
            connection.setUseCaches(false);
            // 设置连接超时时间为30秒
            connection.setConnectTimeout(TIMEOUT);
            // 设置请求模式为POST
            connection.setRequestMethod("POST");
            // 设置POST信息，这里为传入的原始URL
            String postData = URLEncoder.encode(originURL.toString(), "utf-8");
            // 输出原始的url
            connection.getOutputStream().write(("url=" + postData).getBytes());
            // 连接百度短视频接口
            connection.connect();
            // 获取返回的字符串
            String responseStr = getResponseStr(connection);
            // 在字符串里获取tinyurl，即短链接
            tinyUrl = getValueByKey(responseStr, "tinyurl");
            // 关闭链接
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tinyUrl;
    }

    /**
     * 通过HttpConnection 获取返回的字符串
     *
     * @param connection
     * @return
     * @throws IOException
     */
    private static String getResponseStr(HttpURLConnection connection) throws IOException {
        StringBuffer result = new StringBuffer();
        // 从连接中获取http状态码
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 如果返回的状态码是OK的，那么取出连接的输入流
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, ENCODING));
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                // 将消息逐行读入结果中
                result.append(inputLine);
            }
        }
        // 将结果转换成String并返回
        return String.valueOf(result);
    }

    /**
     * JSON 依据传入的key获取value
     *
     * @param replyText
     * @param key
     * @return
     */
    private static String getValueByKey(String replyText, String key) {
        ObjectMapper mapper = new ObjectMapper();
        // 定义json节点
        JsonNode node;
        String targetValue = null;
        try {
            // 把调用返回的消息串转换成json对象
            node = mapper.readTree(replyText);
            // 依据key从json对象里获取对应的值
            targetValue = node.get(key).asText();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return targetValue;
    }

    /**
     *  百度短链接接口
     *
     * @param args
     */
    public static void main(String[] args) {
        getShortURL("https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login");
    }
}
