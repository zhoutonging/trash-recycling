package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.enums.ProductStatusEnum;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.mapper.ProductMapper;
import com.mengzhou.trashrecycling.service.ProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-08-06
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void save(Product product) {

        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setStatus(ProductStatusEnum.UP.getCode());
        productMapper.insert(product);

        redisUtil.remove("productList");
        List<Product> productList = productMapper.selectList(new EntityWrapper<Product>().eq("status", 0));
        redisUtil.set("productList", productList);
    }

    @Override
    public void remove(Integer id) {
        productMapper.deleteById(id);

        redisUtil.remove("productList");
        List<Product> productList = productMapper.selectList(new EntityWrapper<Product>().eq("status", 0));
        redisUtil.set("productList", productList);
    }

    @Override
    public void modifyById(Product product) {
        product.setUpdateTime(new Date());
        productMapper.updateById(product);
    }

    @Override
    public void modifyByStatus(Integer id, Integer status) {
        Product product = productMapper.selectById(id);

        //1.下架 0上架
        if (status == 1) {
            product.setStatus(ProductStatusEnum.UP.getCode());
            productMapper.updateById(product);
            return;
        }
        product.setStatus(ProductStatusEnum.DOWN.getCode());
        productMapper.updateById(product);

    }

    @Override
    public Product findById(Integer id) {
        return productMapper.selectById(id);
    }

    @Override
    public Map<String, Object> findByIdWechar(Integer id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (id == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "查询错误id为空");
                log.error("(微信)根据id查询商品时出现错误,id为空");
                return modelMap;
            }
            Product product = productMapper.selectById(id);


            modelMap.put("success", true);
            modelMap.put("data", product);
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "根据id查询商品时出现异常");
            log.error("(微信)根据id查询商品时出现异常:" + e.getMessage());

            return modelMap;
        }

    }

    @Override
    public List<Product> findAll(Product product) {


        //如果product为空则查询所有
        if (product == null) {
            List<Product> productList = productMapper.selectList(new EntityWrapper<Product>().orderBy("createTime", false));
            redisUtil.set("productList", productList);

            return productList;
        }

        //根据商品名称查询商品
        if (product.getStatus() == null) {
            List<Product> productList = productMapper.selectList(new EntityWrapper<Product>()
                    .orderBy("createTime", false).like("productName", product.getProductName()));

            return productList;
        }


        //根据商品名称查询商品和商品状态
        List<Product> productList = productMapper.selectList(new EntityWrapper<Product>()
                .orderBy("createTime", false)
                .like("productName", product.getProductName())
                .eq("status", product.getStatus()));

        return productList;

    }

    @Override
    public Map<String, Object> findAllByWechar(String productName) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (productName == null) {
                //上架的商品
                List<Product> productList = productMapper.selectList(new EntityWrapper<Product>()
                        .eq("status", 0));

                modelMap.put("success", true);
                modelMap.put("data", productList);

                return modelMap;
            }

            //根据商品名查询
            List<Product> productList = productMapper.selectList(new EntityWrapper<Product>()
                    .eq("status", 0).like("productName", productName));

            modelMap.put("success", true);
            modelMap.put("data", productList);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询所有商品或模糊查询的时候出现异常");
            log.error("(微信)查询所有商品或模糊查询的时候出现异常:" + e.getMessage());
            return modelMap;
        }

    }
}
