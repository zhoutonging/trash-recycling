package com.mengzhou.trashrecycling.mapper;

import com.mengzhou.trashrecycling.common.Dto.CommunityDto;
import com.mengzhou.trashrecycling.model.Community;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-11-09
 */
public interface CommunityMapper extends BaseMapper<Community> {

    /**
     * 查询社区动态
     *
     * @return
     */
    List<CommunityDto> findAll();
}
