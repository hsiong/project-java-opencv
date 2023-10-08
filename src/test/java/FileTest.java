import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.junit.Test;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/9/26
 */
public class FileTest {

    @Test
    public void test() {
        // 设置缩小后的宽度和高度
        int newWidth = 456;
        int newHeight = 80;
        Size size = new org.bytedeco.opencv.opencv_core.Size(newWidth, newHeight);

        // PNG 格式支持透明通道，而其他格式如 JPEG 不支持。
        String inputImagePath = "/Users/vjf/Desktop/8192.png";
        String outputFilePath = "/Users/vjf/Desktop/outputb.png";
        FileUtil.generateOutFile(outputFilePath);

        Mat inputImage = opencv_imgcodecs.imread(inputImagePath, opencv_imgcodecs.IMREAD_UNCHANGED);
        // 创建透明背景的 Mat 对象
        Mat outputImage = new Mat(newHeight, newWidth, inputImage.type());


        /**
         * 缩小图像，使用双三次插值插值
         *
         * @双三次插值插值: 双三次插值是一种高质量的插值算法，通常比双线性插值产生更平滑的结果。您可以使用 OpenCV 中的 resize 函数，并将插值算法设置为 opencv_imgproc.INTER_CUBIC 来应用双三次插值。
         * @Lanczos插值： Lanczos 插值是一种高质量的插值算法，它可以产生锐利的图像。您可以使用 OpenCV 中的 resize 函数，并将插值算法设置为 opencv_imgproc.INTER_LANCZOS4 来应用 Lanczos 插值。
         */
        opencv_imgproc.resize(inputImage, outputImage, size, 0, 0, opencv_imgproc.INTER_CUBIC);
        
        // 保存缩小后的图像
        opencv_imgcodecs.imwrite(outputFilePath, outputImage);

    }

    /**
     * 高斯滤波
     */
    private void gaussianBlur(Mat src) {
        /**
         * 使用高斯滤波器平滑图像
         * 
         * @内核大小： 内核大小是一个整数，它指定了高斯核的大小。这个参数决定了模糊的程度。通常，内核大小必须是正奇数，例如，3、5、7 等。
         *  模糊程度：内核大小越大，模糊效果越明显。较大的内核将平均更多的像素值，导致图像变得更加模糊。
         *  去噪：较大的内核通常对噪声具有更好的去除效果，因为它们考虑了更多周围像素的信息。然而，如果内核过大，可能会损失一些图像细节。
         *  运算成本：较大的内核需要更多的计算，因此处理时间更长。在实时应用中，选择合适的内核大小以平衡去噪效果和计算成本非常重要。
         *  边缘保留：小内核相对于图像中的边缘保留更好，因为它们不会混合跨越边缘的像素。较大的内核可能会导致边缘模糊。
         *  平滑程度：内核大小也会影响平滑程度。较小的内核产生轻微的平滑效果，而较大的内核产生更显著的平滑效果。
         *
         * @标准差（sigma）： 标准差是一个浮点数，控制了高斯分布的扩散程度。较大的标准差将导致高斯分布更广泛，从而产生更强的模糊效果。标准差越大，模糊效果越明显。
         */
        opencv_imgproc.GaussianBlur(src, src, new Size(3, 3), 0.45);

        /**
         * 使用均值滤波器平滑图像, 它使用像素周围的平均值来替代中心像素的值。
         */
        opencv_imgproc.blur(src, src, new Size(3, 3));

    }

}
