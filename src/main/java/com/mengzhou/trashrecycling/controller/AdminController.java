package com.mengzhou.trashrecycling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 路由控制器
 *
 * @author ZHOUTONG
 * @date 2019年08月06日 19:18
 */
@Controller
public class AdminController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/Index")
    public String Index() {
        return "Index";
    }

    /**
     * 桌面
     *
     * @return
     */
    @GetMapping("/home")
    public String console() {
        return "home";
    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 商品管理首页
     *
     * @return
     */
    @GetMapping("productIndex")
    public String productIndex() {
        return "product/productIndex";
    }

    /**
     * 商品添加
     *
     * @return
     */
    @GetMapping("productSave")
    public String productSave() {
        return "product/productSave";
    }

    /**
     * 商品查看
     *
     * @return
     */
    @GetMapping("productFind")
    public String productFind() {
        return "product/productFind";
    }

    /**
     * 商品修改
     *
     * @return
     */
    @GetMapping("productUpdate")
    public String productUpdate() {
        return "product/productUpdate";
    }


    /**
     * 订单首页
     *
     * @return
     */
    @GetMapping("orderIndex")
    public String orderIndex() {
        return "orders/orderIndex";
    }

    /**
     * 广告首页
     *
     * @return
     */
    @GetMapping("slideShowIndex")
    public String slideShowIndex() {
        return "slideShow/slideShowIndex";
    }

    /**
     * 广告首页
     *
     * @return
     */
    @GetMapping("slideShowSave")
    public String slideShowSave() {
        return "slideShow/slideShowSave";
    }

    /**
     * 广告查看
     *
     * @return
     */
    @GetMapping("slideShowFind")
    public String slideShowFind() {
        return "slideShow/slideShowFind";
    }

    /**
     * 广告修改
     *
     * @return
     */
    @GetMapping("slideShowUpdate")
    public String slideShowUpdate() {
        return "slideShow/slideShowUpdate";
    }

    /**
     * 广告修改
     *
     * @return
     */
    @GetMapping("addressIndex")
    public String addressIndex() {
        return "address/addressIndex";
    }


    /**
     * 招聘首页
     *
     * @return
     */
    @GetMapping("recruitmentIndex")
    public String RecruitmentIndex() {
        return "recruitment/recruitmentIndex";
    }

    /**
     * 招聘查看
     *
     * @return
     */
    @GetMapping("recruitmentFind")
    public String recruitmentFind() {
        return "recruitment/recruitmentFind";
    }

    /**
     * 招聘修改
     *
     * @return
     */
    @GetMapping("recruitmentUpdate")
    public String recruitmentUpdate() {
        return "recruitment/recruitmentUpdate";
    }

    /**
     * 招聘添加
     *
     * @return
     */
    @GetMapping("recruitmentSave")
    public String recruitmentSave() {
        return "recruitment/recruitmentSave";
    }

    /**
     * 签到积分设置首页
     *
     * @return
     */
    @GetMapping("signinIndex")
    public String signinIndex() {
        return "signin/signinIndex";
    }

    /**
     * 签到积分设置首页
     *
     * @return
     */
    @GetMapping("recyclecategoryIndex")
    public String recyclecategoryIndex() {
        return "recyclecategory/recyclecategoryIndex";
    }


}
