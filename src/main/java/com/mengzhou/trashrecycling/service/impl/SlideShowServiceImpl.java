package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.model.SlideShow;
import com.mengzhou.trashrecycling.mapper.SlideShowMapper;
import com.mengzhou.trashrecycling.service.SlideShowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-08-06
 */
@Service
@Slf4j
public class SlideShowServiceImpl extends ServiceImpl<SlideShowMapper, SlideShow> implements SlideShowService {

    @Autowired
    private SlideShowMapper slideShowMapper;

    @Override
    public void save(SlideShow slideShow) {
        slideShow.setCreateTime(new Date());
        //TODO 枚举类
        slideShow.setStatus(1);
        slideShowMapper.insert(slideShow);
    }

    @Override
    public SlideShow findById(Integer id) {
        try {
            return slideShowMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据Id查询广告信息时出错:" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<SlideShow> findAll() {
        try {
            List<SlideShow> slideShowList = slideShowMapper.selectList(new EntityWrapper<SlideShow>()
                    .orderBy("createTime", false));
            return slideShowList;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询广告列表时出错" + e.getMessage());
            return null;
        }
    }
}
