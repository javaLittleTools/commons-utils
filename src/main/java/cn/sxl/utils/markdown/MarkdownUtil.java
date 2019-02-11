package cn.sxl.utils.markdown;

import org.pegdown.PegDownProcessor;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author SxL
 * @since 1.3.0
 * 2019-02-11 17:39
 */
public class MarkdownUtil {
    private static String line = null;
    private static StringBuilder mdContent = new StringBuilder();
    private static PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);

    public static String transferToHtml(File mdFile) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mdFile), StandardCharsets.UTF_8));

        while ((line = br.readLine()) != null) {
            mdContent.append(line).append("\r\n");
        }

        return pdp.markdownToHtml(mdContent.toString());
    }

    public static String transferToHtml(File mdFile, String charset) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mdFile), charset));

        while ((line = br.readLine()) != null) {
            mdContent.append(line).append("\r\n");
        }

        return pdp.markdownToHtml(mdContent.toString());
    }
}
