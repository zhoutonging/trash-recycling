package com.mengzhou.trashrecycling.common.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 社区动态DTO
 *
 * @author ZHOUTONG
 * @date 2019年11月09日 9:38
 */
@Data
public class CommunityDto {

    private Integer id;

    /**
     * 发表内容
     */
    private String communityContent;

    /**
     * 区域
     */
    private String area;

    /**
     * 图片
     */
    private String communityIcon;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

}
