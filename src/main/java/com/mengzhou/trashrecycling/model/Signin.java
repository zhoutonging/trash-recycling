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
 * 用户签到模型
 *
 * @author CC
 * @since 2019-09-25
 */
@Data
public class Signin extends Model<Signin> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 签到积分
     */
    @TableField("signinCount")
    private Integer signinCount;
    /**
     * 修改时间
     */
    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
