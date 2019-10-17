package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 手环定位模型
 *
 * @author CC
 * @since 2019-10-15
 */
@Data
public class Wristband extends Model<Wristband> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 地图类型:目前只支持高德
     */
    private String type;
    /**
     * 手环唯一序列
     */
    private String imei;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 经度
     */
    private String lon;
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
