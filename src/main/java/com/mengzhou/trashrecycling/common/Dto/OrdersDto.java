package com.mengzhou.trashrecycling.common.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 订单DTO
 *
 * @author ZHOUTONG
 * @date 2019年10月31日 8:21
 */
@Data
public class OrdersDto {

    private String id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图
     */
    private String productIcon;

    /**
     * 单价
     */
    private Integer productPrice;

    /**
     * 数量
     */
    private Integer productCount;

    /**
     * 费用
     */
    private Integer integral;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
