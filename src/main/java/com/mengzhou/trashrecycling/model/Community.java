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
 * 社区动态模型
 *
 * @author CC
 * @since 2019-11-09
 */
@Data
public class Community extends Model<Community> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * openId
     */
    @TableField("openId")
    private String openId;
    /**
     * 发表内容
     */
    @TableField("communityContent")
    private String communityContent;
    /**
     * 区域
     */
    private String area;
    /**
     * 图片
     */
    @TableField("communityIcon")
    private String communityIcon;
    /**
     * 0 审核通过 1 未审核or未审核通过
     */
    private Integer status;
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
