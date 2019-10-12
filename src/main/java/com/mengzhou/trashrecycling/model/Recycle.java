package com.mengzhou.trashrecycling.model;

import java.util.Date;
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
 * @since 2019-10-12
 */
public class Recycle extends Model<Recycle> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getRecycleCategoryId() {
        return recycleCategoryId;
    }

    public void setRecycleCategoryId(Integer recycleCategoryId) {
        this.recycleCategoryId = recycleCategoryId;
    }

    public String getRecycleName() {
        return recycleName;
    }

    public void setRecycleName(String recycleName) {
        this.recycleName = recycleName;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecycleImg() {
        return recycleImg;
    }

    public void setRecycleImg(String recycleImg) {
        this.recycleImg = recycleImg;
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
        return "Recycle{" +
        ", id=" + id +
        ", addressId=" + addressId +
        ", recycleCategoryId=" + recycleCategoryId +
        ", recycleName=" + recycleName +
        ", appointmentTime=" + appointmentTime +
        ", message=" + message +
        ", recycleImg=" + recycleImg +
        ", createTime=" + createTime +
        "}";
    }
}
