package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 手环语音服务
 *
 * @author CC
 * @since 2019-10-15
 */
@Data
public class Audio extends Model<Audio> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 串号
     */
    private String imei;
    /**
     * 语音数据，语音为amr格式，采用base64 编码的字节信息
     */
    private String audio;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
