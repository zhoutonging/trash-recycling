package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.model.Wristband;
import com.mengzhou.trashrecycling.service.WristbandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 手环控制器
 *
 * @author CC
 * @since 2019-10-15
 */
@RestController
@RequestMapping("/wristband")
public class WristbandController {

    @Autowired
    private WristbandService wristbandService;

    @PostMapping("location")
    public Map<String, Object> location(@RequestBody Wristband wristband) {
       return wristbandService.save(wristband);
    }


}

