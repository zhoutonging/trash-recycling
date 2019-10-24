package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Cabinetlocation;
import com.mengzhou.trashrecycling.service.CabinetlocationService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 柜机前端控制器
 *
 * @author CC
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/cabinetlocation/")
public class CabinetlocationController {

    @Autowired
    private CabinetlocationService cabinetlocationService;

    /**
     * 添加柜机
     *
     * @param cabinetlocation
     * @return
     */
    @PostMapping("save")
    public LayuiResult save(Cabinetlocation cabinetlocation) {
        return cabinetlocationService.save(cabinetlocation);
    }

    /**
     * 根据id删除柜机信息
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult save(Integer id) {
        return cabinetlocationService.deleteById(id);
    }

    /**
     * 根据id更新柜机信息
     *
     * @param cabinetlocation
     * @return
     */
    @PostMapping("modifyById")
    public LayuiResult modifyById(Cabinetlocation cabinetlocation) {
        return cabinetlocationService.modifyById(cabinetlocation);
    }


    /**
     * 查询所有柜机位置
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit, String cabinetName) {
        PageHelper.startPage(page, limit);

        List<Cabinetlocation> cabinetlocationList = cabinetlocationService.findAll(cabinetName);

        PageInfo pageInfo = new PageInfo(cabinetlocationList);

        return LayuiResult.success(pageInfo.getTotal(), cabinetlocationList);
    }

    /**
     * 根据id查询柜子信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(Integer id) {
        return cabinetlocationService.findById(id);
    }

}

