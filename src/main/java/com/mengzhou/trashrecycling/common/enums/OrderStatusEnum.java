package com.mengzhou.trashrecycling.common.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    /**
     * 已支付
     */
    UP(0, "已上架商品"),
    /**
     * 商品下架状态
     */
    DOWN(1, "下架商品");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
