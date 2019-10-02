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
 * 招聘模型
 *
 * @author CC
 * @since 2019-09-22
 */
@Data
public class Recruitment extends Model<Recruitment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 招聘信息
     */
    @TableField("recruitmentName")
    private String recruitmentName;
    /**
     * 招聘内容
     */
    @TableField("recruitmentContent")
    private String recruitmentContent;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系方式
     */
    @TableField("mobilePhone")
    private String mobilePhone;
    /**
     * 发布日期
     */
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
