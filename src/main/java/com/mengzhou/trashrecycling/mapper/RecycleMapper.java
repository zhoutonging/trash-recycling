package com.mengzhou.trashrecycling.mapper;

import com.mengzhou.trashrecycling.common.Dto.RecycleDto;
import com.mengzhou.trashrecycling.model.Recycle;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-10-12
 */
public interface RecycleMapper extends BaseMapper<Recycle> {

    /**
     * 连表查询上门垃圾回收信息
     *
     * @param id
     * @return
     */
    List<RecycleDto> findAll(@Param("id") String id);
}
