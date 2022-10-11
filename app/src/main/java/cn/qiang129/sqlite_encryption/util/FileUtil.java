package cn.qiang129.sqlite_encryption.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {

    /**
     * 复制文件/文件夹 若要进行文件夹复制，请勿将目标文件夹置于源文件夹中
     *
     * @param source   源文件（夹）
     * @param target   目标文件（夹）
     * @throws Exception
     */
    public static void copy(String source, String target) throws Exception {
        int byteread = 0;
        File oldfile = new File(source);
        if (oldfile.exists()) {
            InputStream inStream = new FileInputStream(source);
            File file = new File(target);
            //file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream fs = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.close();
        }
    }

}
