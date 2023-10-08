import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/10/8
 */
public class JavacvTest {

    public static void main(String[] args) throws InterruptedException {

        // 加载图像
        Mat image = opencv_imgcodecs.imread("/Users/vjf/Desktop/2048.png");

        // 在窗口中显示图像
        CanvasFrame canvas = new CanvasFrame("Image");
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.showImage(new OpenCVFrameConverter.ToMat().convert(image));

        // 等待窗口关闭
        canvas.waitKey();
        
    }

}
