package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Product;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品信息业务逻辑层
 *
 * @author CC
 * @since 2019-08-06
 */
public interface ProductService extends IService<Product> {

    /**
     * 添加商品
     *
     * @param product
     */
    void save(Product product);

    /**
     * 根据Id删除商品
     *
     * @param id
     */
    void remove(Integer id);

    /**
     * 根据Id修改商品信息
     *
     * @param product
     */
    void modifyById(Product product);

    /**
     * 修改商品状态
     *
     * @param status
     */
    void modifyByStatus(Integer id, Integer status);

    /**
     * 根据Id查询商品信息
     *
     * @param id
     * @return
     */
    Product findById(Integer id);

    /**
     * 根据Id查询商品信息(微信)
     *
     * @param id
     * @return
     */
    Map<String, Object> findByIdWechar(Integer id);

    /**
     * 查询商品信息
     *
     * @return
     */
    List<Product> findAll(Product product);

    /**
     * 查询商品信息(微信)
     *
     * @return
     */
    Map<String, Object>  findAllByWechar(String productName);
}
