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
 * 手环心率血压血氧模型
 *
 * @author CC
 * @since 2019-10-15
 */
@Data
public class Health extends Model<Health> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 串号
     */
    private String imei;
    /**
     * 心率
     */
    @TableField("heartRate")
    private Integer heartRate;
    /**
     * 低压 
     */
    private Integer dbp;
    /**
     * 高压
     */
    private Integer sdp;
    /**
     * 血氧 
     */
    private Integer oxygen;
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
