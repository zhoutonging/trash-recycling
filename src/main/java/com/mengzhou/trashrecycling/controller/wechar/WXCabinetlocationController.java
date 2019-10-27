package com.mengzhou.trashrecycling.controller.wechar;


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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 柜机前端控制器(微信)
 *
 * @author CC
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/api/cabinetlocation/")
public class WXCabinetlocationController {

    @Autowired
    private CabinetlocationService cabinetlocationService;


    /**
     * 查询距离最近的柜机信息
     *
     * @return
     */
    @GetMapping("findNearby")
    public Map<String, Object> findNearby(Double lon, Double lat) {
        return cabinetlocationService.findNearby(lon, lat);
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

