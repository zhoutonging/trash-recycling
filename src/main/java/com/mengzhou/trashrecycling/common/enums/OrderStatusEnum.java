package com.mengzhou.trashrecycling.common.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    //0.已支付 1.未支付 2.支付未发货 3.支付已经发货

    SUCCESS(0, "已支付"),
    WAIT(1, "等待支付"),
    CANCEL(2, "已取消"),
    FINISHED(3, "支付已发货"),

    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
