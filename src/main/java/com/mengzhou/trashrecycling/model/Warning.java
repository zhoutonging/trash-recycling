package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 手环预警模型
 *
 * @author CC
 * @since 2019-10-15
 */
@Data
public class Warning extends Model<Warning> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 串号
     */
    private String imei;
    /**
     * “1” sos 预警  “2” 跌倒预警 “3” 脱手预警 “4” 久坐预警 “5” 低电预警 “6” 家庭医生
     */
    @TableField("warningType")
    private String warningType;
    /**
     * 低电预警则有值，其他预警无值
     */
    private Integer battery;
    /**
     * SOS 预警则有值，其他预警无值
     */
    private String address;
    /**
     * SOS 高德经度，如果是 SOS 预警则有值， 其他预警无值；如果定位失败，则无值
     */
    private String longitude;
    /**
     * SOS 高德纬度；如果是 SOS 预警则有值， 其他预警无值；如果定位失败，则无值
     */
    private String latitude;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
