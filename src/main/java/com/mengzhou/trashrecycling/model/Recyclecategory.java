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
 * @since 2019-10-05
 */
public class Recyclecategory extends Model<Recyclecategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 类别名称
     */
    @TableField("recycleCategoryName")
    private String recycleCategoryName;
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

    public String getRecycleCategoryName() {
        return recycleCategoryName;
    }

    public void setRecycleCategoryName(String recycleCategoryName) {
        this.recycleCategoryName = recycleCategoryName;
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
        return "Recyclecategory{" +
        ", id=" + id +
        ", recycleCategoryName=" + recycleCategoryName +
        ", createTime=" + createTime +
        "}";
    }
}
