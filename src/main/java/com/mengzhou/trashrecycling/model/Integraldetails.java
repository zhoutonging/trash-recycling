package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 积分明细表
 *
 * @author CC
 * @since 2019-10-26
 */
@Data
public class Integraldetails extends Model<Integraldetails> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 获取积分名称
     */
    @TableField("integralName")
    private String integralName;
    /**
     * 获取的积分
     */
    private String integral;
    /**
     * 用户标识
     */
    @TableField("openId")
    private String openId;
    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
