package com.mengzhou.trashrecycling.common.Dto;

import lombok.Data;

/**
 * 微信信息模型
 *
 * @author ZHOUTONG
 * @date 2019年10月11日 9:58
 */
@Data
public class WecharDto {

    private String sessionKey;

    private String openId;
}
