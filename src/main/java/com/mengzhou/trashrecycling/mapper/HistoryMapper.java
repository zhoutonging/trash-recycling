package com.mengzhou.trashrecycling.mapper;

import com.mengzhou.trashrecycling.model.History;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 微信用户访问记录数据访问层
 *
 * @author CC
 * @since 2019-11-17
 */
public interface HistoryMapper extends BaseMapper<History> {

    /**
     * 查询近七天的访问小程序的用户量
     *
     * @return
     */
    List<Map<String, Object>> find7Count();

    /**
     * 查询今天是否已经登录过小程序
     *
     * @param openId
     * @return
     */
    History findByOpenId(@Param("openId") String openId);
}
