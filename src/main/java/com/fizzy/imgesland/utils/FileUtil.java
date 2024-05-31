package com.fizzy.imgesland.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

    //拦截的url，虚拟路径
    public static final String pathPattern = "image";

    //自己设置的目录
    //public static final String fileDir = "D:\\MyProject\\images\\";
    public static final String fileDir = "/usr/local/image/";

    public static String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String hashFileName = getFileName() + fileType;

        String path = fileDir;
        String absolutePath = path + hashFileName;

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
        return pathPattern + "/" + hashFileName;
    }

    private static String getFileName() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String format = df.format(date);
        return md5(format);
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
