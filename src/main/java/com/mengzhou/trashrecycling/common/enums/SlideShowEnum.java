package com.mengzhou.trashrecycling.common.enums;

import lombok.Getter;

/**
 * 广告状态
 */
@Getter
public enum SlideShowEnum {

    /**
     * 商品上架状态
     */
    USABLE(0, "可用"),
    /**
     * 商品下架状态
     */
    DISABLED(1, "禁用");

    private Integer code;

    private String msg;

    SlideShowEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
