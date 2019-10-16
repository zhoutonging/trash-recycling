package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 手环电量/计步数据
 *
 * @author CC
 * @since 2019-10-15
 */
@Data
public class Beatheart extends Model<Beatheart> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 串号
     */
    private String imei;
    /**
     * 电量 
     */
    private Integer battery;
    /**
     * 计步数据
     */
    private Integer pedometer;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
