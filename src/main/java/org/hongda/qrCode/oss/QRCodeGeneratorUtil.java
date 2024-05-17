package org.hongda.qrCode.oss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @ClassName QRCodeGeneratorUtil
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/13 19:37
 **/
public class QRCodeGeneratorUtil {
    private final static String CHARSET ="utf-8";

    private final static int QRSIZEE =300;

    // 二维码颜色
    private static final int BLACK =0xFF000000;
    // 二维码颜色
    private static final int WHITE =0xFFFFFFFF;

    /**
     * @Description: 把资源转化BufferedImage
     * @Param: [content：需要生成二维码的资源]
     * @return:
     * @Author: lyb
     * @Date: 2024/5/13
     */

    public static BufferedImage createImage(String content){
        Hashtable<EncodeHintType, Object> hints =new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN,1);
        BitMatrix bitMatrix =null;
        try {
            bitMatrix =new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRSIZEE, QRSIZEE,hints);
        }catch (Exception e){
            e.printStackTrace();
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x =0; x < width; x++) {
            for (int y =0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }




    /**
     * @Description: 把BufferedImage通过转化字节码后转化MultipartFile，为上传oss做准备
     * @Param: [content:需要生成二维码的资源]
     * @return:
     * @Author: lydms
     * @Date: 2024/5/13
     */

    public static MultipartFile getMultipartFile(String content) {
        BufferedImage image = createImage(content);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        MultipartFile multipartFile = null;
        try {
            ImageIO.write(image, "jpg", bos);
            multipartFile = new MockMultipartFile("test.jpeg", "test.jpeg", "", bos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return multipartFile;
    }

}
