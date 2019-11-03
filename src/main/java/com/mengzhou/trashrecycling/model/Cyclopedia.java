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
 * 环保百科模型
 *
 * @author CC
 * @since 2019-11-03
 */
@Data
public class Cyclopedia extends Model<Cyclopedia> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 环保百科名称
     */
    @TableField("cyclopediaName")
    private String cyclopediaName;
    /**
     * 展示图
     */
    @TableField("cyclopediaIcon")
    private String cyclopediaIcon;
    /**
     * 描述
     */
    @TableField("cyclopediaDesc")
    private String cyclopediaDesc;
    /**
     * 环保百科内容
     */
    @TableField("cyclopediaContent")
    private String cyclopediaContent;
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
