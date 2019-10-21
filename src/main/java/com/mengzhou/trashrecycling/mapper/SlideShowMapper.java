package com.mengzhou.trashrecycling.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mengzhou.trashrecycling.common.Dto.SlideShowDto;
import com.mengzhou.trashrecycling.model.SlideShow;

import java.util.List;

/**
 * 首页轮播数据访问层
 *
 * @author CC
 * @since 2019-08-06
 */
public interface SlideShowMapper extends BaseMapper<SlideShow> {

    List<SlideShowDto> findAll();
}
