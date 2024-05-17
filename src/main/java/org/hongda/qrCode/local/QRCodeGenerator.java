package org.hongda.qrCode.local;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName QRCodeGenerator
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/12 20:48
 **/
public class QRCodeGenerator {
    public static void main(String[] args) {
        // 资源链接
        String qrCodeData = "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_10049182755003631250%22%7D&n_type=-1&p_from=-1"; // 你要编码的数据
        int qrCodeWidth = 100; // 二维码宽度
        int qrCodeHeight = 100; // 二维码高度

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight);

            // 设置一些编码选项
            Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            // 将BitMatrix转换为图像并保存
            // 设置生成二维的名字
            String fileName = UUID.randomUUID().toString() + ".png";
            // 设置二维码存放的位置
            String filePath = "E:\\temp\\" + fileName;
            File qrCodeFile = new File(filePath);
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", qrCodeFile);

            System.out.println("QR Code image created successfully: " + qrCodeFile.getAbsolutePath());

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
