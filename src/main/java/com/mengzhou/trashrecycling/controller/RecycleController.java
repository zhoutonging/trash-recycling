package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.common.Dto.RecycleDto;
import com.mengzhou.trashrecycling.model.Recycle;
import com.mengzhou.trashrecycling.service.RecycleService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 上门回收垃圾控制器
 *
 * @author CC
 * @since 2019-10-12
 */
@RestController
@RequestMapping("/recycle/")
public class RecycleController {

    @Autowired
    private RecycleService recycleService;

    /**
     * 根据id删除垃圾回收信息
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(String id) {
        return recycleService.deleteById(id);
    }

    /**
     * 修改回收状态
     *
     * @param recycle
     * @return
     */
    @PostMapping("modifyByStatus")
    public LayuiResult modifyById(Recycle recycle) {
        return recycleService.modifyByStatus(recycle);
    }

    /**
     * 根据id查看垃圾回收信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(String id) {
        return recycleService.findById(id);
    }

    /**
     * 垃圾回收成功,添加积分
     *
     * @param recycle
     * @return
     */
    @PostMapping("modifyByIntegral")
    public LayuiResult modifyByIntegral(Recycle recycle) {
        return recycleService.modifyByIntegral(recycle);
    }

    /**
     * 查询垃圾回收信息
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "10") Integer limit,RecycleDto recycleDto) {
        PageHelper.startPage(page, limit);
        List<RecycleDto> recycleList = recycleService.findAllJOIN(recycleDto);

        PageInfo pageInfo = new PageInfo(recycleList);
        return LayuiResult.success(pageInfo.getTotal(), pageInfo.getList());
    }
}

