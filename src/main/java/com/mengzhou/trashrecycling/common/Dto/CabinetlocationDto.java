package com.mengzhou.trashrecycling.common.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 柜机位置DTO
 *
 * @author ZHOUTONG
 * @date 2019年10月25日 20:46
 */
@Data
public class CabinetlocationDto {

    private Integer id;
    /**
     * 柜机名称
     */
    private String cabinetName;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 经度
     */
    private String lon;

    /**
     * 距离位置
     */
    private Double dist;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
