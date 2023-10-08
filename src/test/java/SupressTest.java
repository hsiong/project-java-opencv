import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/10/8
 */
public class SupressTest {

    public static void main(String[] args) {

        // 输入的 PNG 文件和输出的 PNG 文件路径
        String inputPath = "/Users/vjf/Desktop/8192.png";
        String outputPath = "/Users/vjf/Desktop/outputb.jpg";
        FileUtil.generateOutFile(outputPath);

        // 读取原始图像
        Mat originalImage = opencv_imgcodecs.imread(inputPath);

        if (originalImage.empty()) {
            System.out.println("无法读取输入图像。");
            return;
        }

        // 创建图像金字塔
        MatVector pyramid = new MatVector(4); // 4级金字塔
        pyramid.put(0, originalImage.clone());

        // 从金字塔的底部级别开始，逐级降采样
        for (int i = 1; i < 4; i++) {
            Mat downsampled = new Mat();
            opencv_imgproc.pyrDown(pyramid.get(i - 1), downsampled);
            pyramid.put(i, downsampled);
        }

        // 超分辨率重建（这里使用了简单的双三次插值）
        Mat superResImage = pyramid.get(3).clone();

        // 新的宽度和高度
        int newWidth = 456; // 新宽度
        int newHeight = 80; // 新高度

        // 调整超分辨率图像的大小
        opencv_imgproc.resize(superResImage, superResImage, new Size(newWidth, newHeight));

        // 保存超分辨率图像
        opencv_imgcodecs.imwrite(outputPath, superResImage);


    }

}

