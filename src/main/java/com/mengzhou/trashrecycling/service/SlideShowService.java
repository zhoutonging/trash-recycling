package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.SlideShow;
import com.baomidou.mybatisplus.service.IService;

/**
 * 首页轮播业务逻辑层
 *
 * @author CC
 * @since 2019-08-06
 */
public interface SlideShowService extends IService<SlideShow> {

    /**
     * 添加轮播图
     *
     * @param slideShow
     */
    void save(SlideShow slideShow);
}
