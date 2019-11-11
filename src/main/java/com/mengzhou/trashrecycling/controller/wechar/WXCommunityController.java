package com.mengzhou.trashrecycling.controller.wechar;


import com.mengzhou.trashrecycling.model.Community;
import com.mengzhou.trashrecycling.service.CommunityService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 社区动态前端控制器(微信)
 *
 * @author CC
 * @since 2019-11-09
 */
@RestController
@RequestMapping("/api/community/")
public class WXCommunityController {

    @Autowired
    private CommunityService communityService;

    /**
     * 添加社区动态
     *
     * @param community
     * @param sessionKey
     * @return
     */
    @PostMapping("save")
    public Map<String, Object> save(Community community, String sessionKey) {
        return communityService.save(community, sessionKey);
    }

    /**
     * 根据id删除社区动态
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return communityService.deleteById(id);
    }

    /**
     * 查询所有社区动态
     *
     * @return
     */
    @GetMapping("findAll")
    public Map<String, Object> findAll() {
        return communityService.findAllWechar();
    }
}

