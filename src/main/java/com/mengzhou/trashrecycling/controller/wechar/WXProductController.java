package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHOUTONG
 * @date 2019年09月09日 16:20
 */
@RestController
@RequestMapping("/api/product/")
public class WXProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有的上架商品OR模糊查询
     *
     * @param productName
     * @return
     */
    @GetMapping("findAll")
    public Map<String, Object> findAll(String productName) {

        Map<String, Object> modelMap = new HashMap<>(16);

        List<Product> productList = productService.findAllByWechar(productName);

        modelMap.put("success", true);
        modelMap.put("data", productList);

        return modelMap;
    }

    /**
     * 根据商品id查询信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Map<String, Object> findById(Integer id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        Product product = productService.findById(id);
        modelMap.put("success", true);
        modelMap.put("data", product);

        return modelMap;
    }
}
