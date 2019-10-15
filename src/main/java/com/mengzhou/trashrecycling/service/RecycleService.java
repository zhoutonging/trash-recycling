package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.common.Dto.RecycleDto;
import com.mengzhou.trashrecycling.model.Recycle;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;
import java.util.Map;

/**
 * 垃圾回收业务逻辑层
 *
 * @author CC
 * @since 2019-10-12
 */
public interface RecycleService extends IService<Recycle> {

    /**
     * 新增上门垃圾回收(微信)
     *
     * @param recycle
     * @return
     */
    Map<String, Object> save(Recycle recycle);

    /**
     * 根据id删除信息
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(Integer id);

    /**
     * 修改上门回收信息
     *
     * @param recycle
     * @return
     */
    Map<String, Object> modifyById(Recycle recycle);

    /**
     * 根据id查询上门回收信息
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);

    /**
     * 查询某个用户下的上门回收信息
     *
     * @param openId
     * @return
     */
    Map<String, Object> findByOpenId(String openId);

    /**
     * 查询所有上门回收信息
     *
     * @return
     */
    List<Recycle> findAll();

    /**
     * 连表查询上门回收信息
     *
     * @return
     */
    List<RecycleDto> findAllJOIN(Integer id);
}
