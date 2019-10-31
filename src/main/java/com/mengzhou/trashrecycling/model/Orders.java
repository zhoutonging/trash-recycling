package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单模型
 *
 * @author CC
 * @since 2019-09-07
 */
@TableName("orders")
@Data
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;

    @TableField("openId")
    private String openId;

    /**
     * 商品名称
     */
    @TableField("productName")
    private String productName;

    /**
     * 单价
     */
    @TableField("productPrice")
    private Integer productPrice;

    /**
     * 收货地址id
     */
    @TableField("addressId")
    private Integer addressId;

    /**
     * 商品图
     */
    @TableField("productIcon")
    private String productIcon;

    /**
     * 数量
     */
    @TableField("productCount")
    private Integer productCount;

    /**
     * 兑换商品所需积分
     */
    @TableField("integral")
    private Integer integral;

    /**
     * 0.已支付 1.支付已经发货
     */
    private Integer status;

    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
