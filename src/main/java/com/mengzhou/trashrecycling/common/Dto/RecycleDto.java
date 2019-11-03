package com.mengzhou.trashrecycling.common.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 上门垃圾回收DTO
 *
 * @author ZHOUTONG
 * @date 2019年10月13日 18:27
 */
@Data
public class RecycleDto {

    private String id;

    private String appointmentTime;//预约时间

    private String categoryName;//回收类别

    private Integer status; //0:未处理 1:已处理 2:已完成

    private Integer integral; //回收成功所得积分

    private String name;//联系人

    private String mobile;//联系方式

    private String recycleName;//回收名称

    private String addressName;//联系地址

    private String message;//留言

    private String recycleImg;//地址

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//创建时间


}
