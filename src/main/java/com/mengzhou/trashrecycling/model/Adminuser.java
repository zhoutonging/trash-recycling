package com.mengzhou.trashrecycling.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户模型
 *
 * @author CC
 * @since 2019-11-05
 */
@Data
public class Adminuser extends Model<Adminuser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密盐
     */
    private String salt;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
