package com.mengzhou.trashrecycling.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户地址模型
 *
 * @author CC
 * @since 2019-09-19
 */
@Data
public class Address extends Model<Address> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户标识
     */
    @TableField("openId")
    private String openId;

    /**
     * 收货人
     */
    @TableField("userName")
    private String userName;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 收货地址的省市区
     */
    private String area;

    /**
     * 收货地址
     */
    private String address;
    /**
     * 是否是默认地址 0 是 1 否
     */
    @TableField("defaultAddress")
    private Integer defaultAddress;
    /**
     * 创建时间
     */
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
