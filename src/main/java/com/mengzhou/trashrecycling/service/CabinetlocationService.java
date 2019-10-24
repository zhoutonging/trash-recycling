package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Cabinetlocation;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;

/**
 * 柜机位置业务逻辑层
 *
 * @author CC
 * @since 2019-10-22
 */
public interface CabinetlocationService extends IService<Cabinetlocation> {

    /**
     * 添加柜机位置
     *
     * @param cabinetlocation
     * @return
     */
    LayuiResult save(Cabinetlocation cabinetlocation);

    /**
     * 删除柜机位置
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(Integer id);

    /**
     * 修改柜机位置
     *
     * @param cabinetlocation
     * @return
     */
    LayuiResult modifyById(Cabinetlocation cabinetlocation);

    /**
     * 根据id查询柜机位置
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);

    /**
     * 查询所有柜机位置
     *
     * @return
     */
    List<Cabinetlocation> findAll(String cabinetName);
}
