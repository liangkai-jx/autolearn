package com.learn.utlis;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/25 19:22
 **/
public class FileUtils {

    public static boolean checkFileSize(MultipartFile file, int size, String unit) {
        long len = file.getSize();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
