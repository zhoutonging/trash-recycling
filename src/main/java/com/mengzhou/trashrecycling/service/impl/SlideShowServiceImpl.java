package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.SlideShow;
import com.mengzhou.trashrecycling.mapper.SlideShowMapper;
import com.mengzhou.trashrecycling.service.SlideShowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-08-06
 */
@Service
public class SlideShowServiceImpl extends ServiceImpl<SlideShowMapper, SlideShow> implements SlideShowService {

    @Autowired
    private SlideShowMapper slideShowMapper;

    @Override
    public void save(SlideShow slideShow) {

        if (slideShow.getImage() == null) {
            throw new RuntimeException("图片不能为空");
        }

        slideShowMapper.insert(slideShow);
    }
}
