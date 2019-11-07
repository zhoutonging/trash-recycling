package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Cyclopedia;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.Map;

/**
 * 环保百科业务逻辑层
 *
 * @author CC
 * @since 2019-11-03
 */
public interface CyclopediaService extends IService<Cyclopedia> {

    /**
     * 添加环保百科
     *
     * @param cyclopedia
     * @return
     */
    LayuiResult save(Cyclopedia cyclopedia);

    /**
     * 根据id删除环保百科
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(Integer id);

    /**
     * 根据id修改环保百科
     *
     * @param cyclopedia
     * @return
     */
    LayuiResult modifyById(Cyclopedia cyclopedia);

    /**
     * 根据id查询环保百科
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);

    /**
     * 分页查询所有环保百科
     *
     * @param page
     * @param limit
     * @return
     */
    LayuiResult findAll(Integer page, Integer limit,String cyclopediaName);

    /**
     * 查询所有环保百科(微信)
     *
     * @return
     */
    Map<String, Object> findAllWechat();

    /**
     * 根据id查询环保百科(微信)
     *
     * @return
     */
    Map<String, Object> findByIdWechat(Integer id);
}
