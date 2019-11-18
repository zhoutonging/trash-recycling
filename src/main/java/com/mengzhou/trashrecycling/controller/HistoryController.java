package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 访客记录前端控制器安
 *
 * @author CC
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/history/")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * 查询微信小程序近7天的访问量
     *
     * @return
     */
    @GetMapping("find7Count")
    public Map<String, Object> find7Count() {
        return historyService.find7Count();
    }
}

