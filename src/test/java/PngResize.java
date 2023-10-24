import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/9/26
 */
public class PngResize {

    @Test
    public void resize() throws Exception {
        // 输入的 PNG 文件和输出的 PNG 文件路径
        String inputFilePath = "/Users/vjf/Desktop/8192.png";
        String outputFilePath = "/Users/vjf/Desktop/outputb.png";
        FileUtil.generateOutFile(outputFilePath);

        // 新的宽度和高度
        int newWidth = 456; // 新宽度
        int newHeight = 80; // 新高度

        // 读取原始图像
        BufferedImage originalImage = ImageIO.read(new File(inputFilePath));
        
        // 创建目标图像
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        // 使用双三次插值缩放算法, 并设置抗锯齿
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();

//        // 保存缩小并锐化后的图像
//        File outputFile = new File(outputFilePath);
//        ImageIO.write(resizedImage, "PNG", outputFile);
//
//        Mat inputImage = opencv_imgcodecs.imread(outputFilePath, opencv_imgcodecs.IMREAD_UNCHANGED);
//        // 创建透明背景的 Mat 对象
//        Mat outputImage = new Mat(newHeight, newWidth, inputImage.type());
//
//        opencv_imgproc.GaussianBlur(inputImage, outputImage, new Size(1, 1), 0.8);
//
//        // 保存缩小后的图像
//        opencv_imgcodecs.imwrite(outputFilePath, outputImage);
        
    }

    
    
}
