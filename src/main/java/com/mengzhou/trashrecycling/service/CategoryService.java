package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Category;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 商品类别业务逻辑层
 *
 * @author CC
 * @since 2019-08-06
 */
public interface CategoryService extends IService<Category> {

    /**
     * 添加类别
     *
     * @param category
     */
    void save(Category category);

    /**
     * 根据Id删除商品类别
     *
     * @param id
     */
    void removeById(Integer id);

    /**
     * 根据Id修改商品类别
     *
     * @param id
     */
    void modifyById(Integer id);

    /**
     * 根据Id查询商品类别
     *
     * @param id
     * @return
     */
    Category findById(Integer id);

    /**
     * 查询所有商品类别
     *
     * @return
     */
    List<Category> findAll();
}
