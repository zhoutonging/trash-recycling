package com.mengzhou.trashrecycling.controller.wechar;


import com.mengzhou.trashrecycling.common.Dto.RecycleDto;
import com.mengzhou.trashrecycling.model.Recycle;
import com.mengzhou.trashrecycling.service.RecycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上门回收垃圾控制器(微信)
 *
 * @author CC
 * @since 2019-10-12
 */
@RestController
@RequestMapping("/api/recycle/")
@Slf4j
public class WXRecycleController {

    @Autowired
    private RecycleService recycleService;

    /**
     * 添加上门回收信息
     *
     * @param recycle
     * @return
     */
    @PostMapping("save")
    public Map<String, Object> save(Recycle recycle, String sessionKey) {
        return recycleService.save(recycle, sessionKey);
    }

    /**
     * 根据id修改上门回收信息
     *
     * @param recycle
     * @return
     */
    @PostMapping("modifyById")
    public Map<String, Object> modifyById(Recycle recycle) {
        return recycleService.modifyById(recycle);
    }

    /**
     * 查询用户下的所有上门回收记录
     *
     * @param sessionKey
     * @return
     */
    @GetMapping("findByOpenId")
    public Map<String, Object> findByOpenId(String sessionKey) {
        return recycleService.findByOpenId(sessionKey);
    }

    /**
     * 根据id查询上门回收信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Map<String, Object> findById(String id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (id == null) {
                modelMap.put("msg", "(微信)根据id查询上门回收信息时出现错误:id为空");

                modelMap.put("success", false);
                return modelMap;
            }
            RecycleDto recycleDto = new RecycleDto();
            recycleDto.setId(id);
            List<RecycleDto> recycleDtoList = recycleService.findAllJOIN(recycleDto);

            modelMap.put("success", true);
            modelMap.put("data", recycleDtoList);
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("msg", "(微信)根据id查询上门回收信息时出现异常");
            modelMap.put("success", false);
            log.error("(微信)根据id查询上门回收信息时出现异常" + e.getMessage());
            return modelMap;
        }
    }

}

