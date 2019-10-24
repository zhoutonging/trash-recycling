package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.model.*;
import com.mengzhou.trashrecycling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 手环相关服务控制器
 *
 * @author CC
 * @since 2019-10-15
 */
@RestController
@RequestMapping("/api/wristband/")
public class WristbandController {

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

    /**
     * 添加手环定位信息
     *
     * @param wristband
     * @return
     */
    @PostMapping("location")
    public Map<String, Object> locationSave(@RequestBody Wristband wristband) {
        return wristbandService.save(wristband);
    }

    /**
     * 添加预警信息
     *
     * @param warning
     * @return
     */
    @PostMapping("warning")
    public Map<String, Object> warningSave(@RequestBody Warning warning) {
        return warningService.save(warning);
    }

    /**
     * 添加心率血压血氧
     *
     * @param health
     * @return
     */
    @PostMapping("health")
    public Map<String, Object> healthSave(@RequestBody Health health) {
        return healthService.save(health);
    }

    /**
     * 添加手环电量/计步信息
     *
     * @param beatheart
     * @return
     */
    @PostMapping("beatHeart")
    public Map<String, Object> beatHeartSave(@RequestBody Beatheart beatheart) {
        return beatheartService.save(beatheart);
    }

    /**
     * 添加语音对讲服务
     *
     * @param audio
     * @return
     */
    @PostMapping("audio")
    public Map<String, Object> audioSave(@RequestBody Audio audio) {
        return audioService.save(audio);
    }

}

