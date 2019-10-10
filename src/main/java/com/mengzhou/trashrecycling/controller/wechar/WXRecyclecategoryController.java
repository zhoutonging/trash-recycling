package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.service.RecyclecategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 垃圾类别管理(微信)
 *
 * @author ZHOUTONG
 * @date 2019年10月08日 10:41
 */
@RestController
@RequestMapping("/api/recyclecategory/")
public class WXRecyclecategoryController {

    @Autowired
    private RecyclecategoryService recyclecategoryService;

    @GetMapping("findAll")
    public Map<String,Object> findAll(){
        return recyclecategoryService.findAllWechar();
    }
}
