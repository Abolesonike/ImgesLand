package com.fizzy.imgesland.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

    //拦截的url，虚拟路径
    public static final String pathPattern = "image";

    //自己设置的目录
    // public static final String fileDir = "D:\\MyProject\\images\\";
    public static final String fileDir = "\\usr\\local\\image";

    public static String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String path = fileDir + getDayString();
        String absolutePath = path + File.separator + fileName;

        //首次需生成目录
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            file.transferTo(new File(absolutePath));
        } catch (IOException e) {
            return "";
        }
        return absolutePath;
    }

    private static String getDayString() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return df.format(date);
    }
}
