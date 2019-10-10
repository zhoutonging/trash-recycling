package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.service.RecruitmentService;
import com.mengzhou.trashrecycling.service.impl.RecruitmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 招聘信息
 *
 * @author ZHOUTONG
 * @date 2019年10月09日 18:41
 */
@RestController
@RequestMapping("/api/recruitment/")
public class WXRecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    /**
     * 根据id查询招聘信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Map<String, Object> findById(Integer id) {
        return recruitmentService.findByIdWechar(id);
    }

    /**
     * 查询招聘列表
     *
     * @return
     */
    @GetMapping("findAll")
    public Map<String, Object> findAll() {
        return recruitmentService.findAllWechar();
    }
}
