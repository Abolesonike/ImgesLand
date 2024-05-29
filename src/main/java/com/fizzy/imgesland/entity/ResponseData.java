package com.fizzy.imgesland.entity;

import lombok.Data;

@Data
public class ResponseData {

    /**
     * 成功标识
     */
    private boolean success;

    /**
     * 编码
     */
    private String code;

    /**
     * 信息
     */
    private String message;

    public ResponseData() {}

    public ResponseData(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
