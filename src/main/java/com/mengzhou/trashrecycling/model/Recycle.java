package com.mengzhou.trashrecycling.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 上门回收垃圾模型
 *
 * @author CC
 * @since 2019-10-12
 */
@Data
public class Recycle extends Model<Recycle> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户openId
     */
    @TableField("openId")
    private String openId;
    /**
     * 收货地址Id
     */
    @TableField("addressId")
    private Integer addressId;
    /**
     * 垃圾类别
     */
    @TableField("recycleCategoryId")
    private Integer recycleCategoryId;
    /**
     * 垃圾名称
     */
    @TableField("recycleName")
    private String recycleName;
    /**
     * 预约时间
     */
    @TableField("appointmentTime")
    private String appointmentTime;
    /**
     * 备注信息
     */
    private String message;
    /**
     * 垃圾图片
     */
    @TableField("recycleImg")
    private String recycleImg;
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
