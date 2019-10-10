package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.SlideShow;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

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
     * 删除广告
     *
     * @param id
     */
    void removeById(Integer id);

    /**
     * 根据Id修改广告
     *
     * @param slideShow
     */
    void modifyById(SlideShow slideShow);

    /**
     * 设置广告状态
     *
     * @param id
     * @param status
     */
    void modifyByStatus(Integer id, Integer status);

    /**
     * 根据id查询广告信息
     *
     * @param id
     * @return
     */
    SlideShow findById(Integer id);

    /**
     * 根据id查询广告信息(微信)
     *
     * @param id
     * @return
     */
    Map<String,Object> findByIdWechar(Integer id);

    /**
     * 查询所有广告
     *
     * @return
     */
    List<SlideShow> findAll();

    /**
     * 查询所有广告(微信)
     *
     * @return
     */
    Map<String, Object> findAllWechar();
}
