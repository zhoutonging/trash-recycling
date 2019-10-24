package com.mengzhou.trashrecycling.controller;

import com.mengzhou.trashrecycling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手环开放接口(安卓APP)
 *
 * @author ZHOUTONG
 * @date 2019年10月23日 17:18
 */
@RestController
@RequestMapping("/api/wristband/")
public class WristbandAPPController {

    @Autowired
    private WristbandService wristbandService;

    @Autowired
    private WarningService warningService;

    @Autowired
    private HealthService healthService;

    @Autowired
    private BeatheartService beatheartService;

    @Autowired
    private AudioService audioService;

    @Value("${wristband.cpid}")
    private String cpid;    //手环CPID

    @Value("${wristband.key}")
    private String key;    //手环KEY


}
