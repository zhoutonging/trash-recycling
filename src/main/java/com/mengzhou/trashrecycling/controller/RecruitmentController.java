package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Address;
import com.mengzhou.trashrecycling.model.Recruitment;
import com.mengzhou.trashrecycling.service.RecruitmentService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 招聘前端控制器
 *
 * @author CC
 * @since 2019-09-22
 */
@RestController
@RequestMapping("/recruitment")
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    /**
     * 添加招聘信息
     *
     * @param recruitment
     * @return
     */
    @PostMapping("save")
    public LayuiResult save(Recruitment recruitment) {
        return recruitmentService.save(recruitment);
    }

    /**
     * 根据Id删除招聘信息
     *
     * @param Id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer Id) {
        return recruitmentService.removeById(Id);
    }

    /**
     * 根据Id更新招聘信息
     *
     * @param recruitment
     * @return
     */
    @PostMapping("modifyById")
    public LayuiResult modifyById(Recruitment recruitment) {
        if (recruitment.getId() == null) {
            return LayuiResult.fail("Id为空");
        }
        recruitmentService.modifyById(recruitment);
        return LayuiResult.success("更新成功");
    }

    /**
     * 根据Id查询招聘
     *
     * @param Id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(Integer Id) {
        if (Id == null) {
            return LayuiResult.fail("Id为空");
        }
        Recruitment recruitment = recruitmentService.findById(Id);

        return LayuiResult.success(recruitment);

    }

    /**
     * 查询招聘列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit,String recruitmentName) {
        //分页
        Page pageObject = PageHelper.startPage(page, limit);

        List<Recruitment> recruitmentList = recruitmentService.findAll(recruitmentName);
        PageInfo pageInfo = new PageInfo(recruitmentList);

        return LayuiResult.success(pageInfo.getTotal(), pageInfo.getList());
    }
}

