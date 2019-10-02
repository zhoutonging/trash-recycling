package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Recruitment;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;

/**
 * 招聘业务逻辑层
 *
 * @author CC
 * @since 2019-09-22
 */
public interface RecruitmentService extends IService<Recruitment> {

    /**
     * 添加招聘信息
     *
     * @param recruitment
     * @return
     */
    LayuiResult save(Recruitment recruitment);

    /**
     * 删除照片信息
     *
     * @param Id
     * @return
     */
    LayuiResult removeById(Integer Id);

    /**
     * 修改招聘信息
     *
     * @param recruitment
     * @return
     */
    LayuiResult modifyById(Recruitment recruitment);

    /**
     * 根据Id查询招聘
     *
     * @param Id
     * @return
     */
    Recruitment findById(Integer Id);

    /**
     * 查询招聘列表
     *
     * @return
     */
    List<Recruitment> findAll(String recruitmentName);
}
