package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;

/**
 * 首页轮播模型
 *
 * @author CC
 * @since 2019-08-06
 */
@TableName("slide_show")
@Data
public class SlideShow extends Model<SlideShow> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 首页轮播图展示
     */
    private String image;

    /**
     * 显示权重
     */
    private Integer sort;
    /**
     * 状态:0展示 1不展示
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("caeateTime")
    @CreatedDate
    private Date caeateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
