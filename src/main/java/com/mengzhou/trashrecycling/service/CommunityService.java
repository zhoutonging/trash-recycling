package com.mengzhou.trashrecycling.service;

import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.model.Community;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.Map;

/**
 * 社区动态业务逻辑层
 *
 * @author CC
 * @since 2019-11-09
 */
public interface CommunityService extends IService<Community> {

    /**
     * 添加社区动态
     *
     * @param community
     * @param sessionKey
     * @return
     */
    Map<String, Object> save(Community community, String sessionKey);

    /**
     * 根据Id删除社区动态(微信)
     *
     * @param id
     * @param sessionKey
     * @return
     */
    Map<String, Object> deleteByIdWechar(Integer id, String sessionKey);

    /**
     * 根据Id删除社区动态(微信)
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(Integer id);

    /**
     * 审核帖子
     *
     * @param id
     * @return
     */
    LayuiResult modifyByStatus(Integer id);

    /**
     * 查询所有社区动态(微信)
     *
     * @return
     */
    Map<String, Object> findAllWechar();

    /**
     * 查询所有社区动态
     *
     * @return
     */
    LayuiResult findAll(Integer page, Integer limit);

    /**
     * 根据id查询社区动态
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);
}
