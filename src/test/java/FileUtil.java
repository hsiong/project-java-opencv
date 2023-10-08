import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/9/25
 */
public class FileUtil {

    public static String getLongText(String filePath) {

        String out = "";
        try {
            // 创建文件读取器
            FileReader fileReader = new FileReader(filePath);

            // 创建缓冲字符流
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // 逐行读取文件内容
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                out += line;
            }

            // 关闭文件读取器
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }
    
    public static File generateOutFile(String jpgFile) {
        File outFile = new File(jpgFile);
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return outFile;
    }

}
