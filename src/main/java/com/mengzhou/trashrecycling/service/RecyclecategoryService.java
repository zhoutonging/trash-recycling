package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Recyclecategory;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;
import java.util.Map;

/**
 * 垃圾回收类别业务逻辑层
 *
 * @author CC
 * @since 2019-10-05
 */
public interface RecyclecategoryService extends IService<Recyclecategory> {

    /**
     * 添加垃圾类别
     *
     * @param recyclecategory
     * @return
     */
    LayuiResult save(Recyclecategory recyclecategory);

    /**
     * 修改垃圾类别
     *
     * @param recyclecategory
     * @return
     */
    LayuiResult modifyById(Recyclecategory recyclecategory);

    /**
     * 根据id删除垃圾类别
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(Integer id);

    /**
     * 根据id查询垃圾类别
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);

    /**
     * 查询垃圾类别
     *
     * @return
     */
    List<Recyclecategory> findAll();

    /**
     * 查询垃圾类别(微信)
     *
     * @return
     */
    Map<String, Object> findAllWechar();

}
