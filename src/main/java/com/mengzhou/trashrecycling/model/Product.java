package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品信息模型
 *
 * @author CC
 * @since 2019-08-06
 */
@Data
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品名称
     */
    @TableField("productName")
    private String productName;
    /**
     * 兑换商品所需要的绿色值
     */
    @TableField("productPrice")
    private Integer productPrice;
    /**
     * 商品描述
     */
    @TableField("productDescription")
    private String productDescription;
    /**
     * 商品图
     */
    @TableField("productIcon")
    private String productIcon;
    /**
     * 商品状态 0 正常 1:下架
     */
    private Integer status;
    /**
     * 所属分类
     */
    @TableField("categoryId")
    private Integer categoryId;
    /**
     * 创建时间
     */
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
