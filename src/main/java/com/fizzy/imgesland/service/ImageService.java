package com.fizzy.imgesland.service;

import com.fizzy.imgesland.entity.ResponseData;
import com.fizzy.imgesland.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public ResponseData upload(MultipartFile image) {
        ResponseData responseData = new ResponseData();
        // 文件检查
        if (image == null) {
            return new ResponseData(false, "400", "文件为空！");
        }
        String fileName = image.getOriginalFilename();
        if (fileName == null) {
            return new ResponseData(false, "400", "传入的文件名不能为空！");
        }
//        if (!this.validateFileType(fileName)) {
//            return new ResponseData(false, "400", "文件类型不支持！");
//        }
        if (!this.validateFileName(fileName)) {
            return new ResponseData(false, "400", "文件名应仅包含汉字、字母、数字、下划线和点号！");
        }
        String filePath = FileUtil.uploadFile(image);
        responseData.setSuccess(true);
        responseData.setMessage(filePath);
        return responseData;
    }

    public ResponseData get() {
        ResponseData responseData = new ResponseData();

        return responseData;
    }

    /**
     * 验证文件名称：仅包含 汉字、字母、数字、下划线和点号
     *
     * @param fileName 文件名称
     * @return 返回true表示符合要求
     */
    private boolean validateFileType(String fileName) {
        String fileTypeRegex = "\\.(jpg|jpeg|png|gif|bmp|tif|tiff|webp|svg)$";
        return fileName.matches(fileTypeRegex);
    }

    /**
     * 验证文件名称：仅包含 汉字、字母、数字、下划线和点号
     *
     * @param fileName 文件名称
     * @return 返回true表示符合要求
     */
    private boolean validateFileName(String fileName) {
        String fileNameRegex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5_\\.\\s]+$";
        return fileName.matches(fileNameRegex);
    }
}
