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
 * 微信小程序访客记录
 *
 * @author CC
 * @since 2019-11-17
 */
@Data
public class History extends Model<History> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * openId
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
