package com.guangbei.util.net;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <img src='data:image/jpg;base64,xxxxx/>
 * Created by xugang on 2017/3/3.
 */
public class ImageUtils {
    /**
     * 将网络图片进行Base64位编码
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(URL imageUrl) {//
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Base64 base64Coder = new Base64();
        return base64Coder.encodeAsString(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * @param imageBytes
     * @return
     */
    public static String encodeBytesToBase64(byte[] imageBytes) {
        // 对字节数组Base64编码
        Base64 base64Coder = new Base64();
        return base64Coder.encodeToString(imageBytes);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64 base64编码的图片信息
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path, String imgName) {
        Base64 base64Coder = new Base64();
        try {
            FileOutputStream write = new FileOutputStream(new File(path + imgName));
            byte[] decoderBytes = base64Coder.decode(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
