package com.mengzhou.trashrecycling.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

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

    @TableId(value = "id", type = IdType.AUTO)
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
     * 数量
     */
    @TableField("productCount")
    private Integer productCount;

    /**
     * 总金额
     */
    @TableField("totalMoney")
    private Integer totalMoney;

    /**
     * 0.已支付 1.未支付 2.已取消 3.支付已经发货
     */
    private Integer status;

    @TableField("createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
