package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CC
 * @since 2019-10-17
 */
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
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", nickName=" + nickName +
        ", avatarUrl=" + avatarUrl +
        ", gender=" + gender +
        ", openId=" + openId +
        ", integral=" + integral +
        ", createTime=" + createTime +
        "}";
    }
}
