package com.fizzy.imgesland.entity;

import lombok.Data;

@Data
public class Image {
    /**
     * 名称
     */
    private String name;

    /**
     * 大小
     */
    private String size;

    /**
     * 类型
     */
    private String type;

    /**
     * 位置
     */
    private String path;
}
