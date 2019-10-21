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
 * 微信用户模型
 *
 * @author CC
 * @since 2019-10-17
 */
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户昵称
     */
    @TableField("nickName")
    private String nickName;
    /**
     * 用户头像地址
     */
    @TableField("avatarUrl")
    private String avatarUrl;
    /**
     * 性别 0：未知、1：男、2：女
     */
    private Integer gender;
    /**
     * 用户唯一标识
     */
    @TableField("openId")
    private String openId;
    /**
     * 用户积分
     */
    private Integer integral;
    /**
     * 注册日期
     */
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
