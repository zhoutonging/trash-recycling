package com.mengzhou.trashrecycling.common.enums;

import lombok.Getter;

@Getter
public enum AddressEnum {


    //0.已支付 1.未支付 2.支付未发货 3.支付已经发货

    DEFAULT(0, "默认地址"),
    NO(1, "不是默认地址"),
    ;

    private Integer code;

    private String message;

    AddressEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
