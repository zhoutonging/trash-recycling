package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.SlideShow;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 首页广告业务逻辑层
 *
 * @author CC
 * @since 2019-08-06
 */
public interface SlideShowService extends IService<SlideShow> {

    /**
     * 添加广告
     *
     * @param slideShow
     */
    void save(SlideShow slideShow);

    /**
     * 设置广告状态
     *
     * @param id
     * @param status
     */
    void modifyByStatus(Integer id, Integer status);

    /**
     * 根据ID查询广告信息
     *
     * @param id
     * @return
     */
    SlideShow findById(Integer id);

    /**
     * 查询所有广告
     *
     * @return
     */
    List<SlideShow> findAll();
}
