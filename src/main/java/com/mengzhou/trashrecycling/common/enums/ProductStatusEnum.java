package com.mengzhou.trashrecycling.common.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    /**
     * 商品上架状态
     */
    UP(0, "已上架商品"),
    /**
     * 商品下架状态
     */
    DOWN(1, "下架商品");

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
