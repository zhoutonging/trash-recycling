package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.enums.SlideShowEnum;
import com.mengzhou.trashrecycling.model.SlideShow;
import com.mengzhou.trashrecycling.mapper.SlideShowMapper;
import com.mengzhou.trashrecycling.service.SlideShowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        slideShow.setStatus(SlideShowEnum.USABLE.getCode());
        slideShowMapper.insert(slideShow);
    }

    @Override
    public void removeById(Integer id) {
        slideShowMapper.deleteById(id);
    }

    @Override
    public void modifyById(SlideShow slideShow) {
        slideShowMapper.updateById(slideShow);
    }

    @Override
    public void modifyByStatus(Integer id, Integer status) {
        try {
            SlideShow slideShow = this.findById(id);

            //1.禁用 0.可用
            if (status == 1) {
                slideShow.setStatus(SlideShowEnum.USABLE.getCode());
                slideShowMapper.updateById(slideShow);
                return;
            }

            slideShow.setStatus(SlideShowEnum.DISABLED.getCode());
            slideShowMapper.updateById(slideShow);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("设置广告状态时出错:" + e.getMessage());
        }
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
    public Map<String, Object> findByIdWechar(Integer id) {
        Map<String, Object> modelMap = new HashMap<>(16);
        try {
            if (id == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "查询广告列表时出现异常");
                log.error("(微信)根据id查询广告列表时出现错误.id为空");
                return modelMap;
            }

            SlideShow slideShow = slideShowMapper.selectById(id);
            modelMap.put("success", true);
            modelMap.put("data", slideShow);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询广告列表时出现异常");
            log.error("(微信)根据id查询广告时出现异常" + e.getMessage());
            return modelMap;
        }

        return modelMap;
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

    @Override
    public Map<String, Object> findAllWechar() {
        Map<String, Object> modelMap = new HashMap<>(16);
        try {
            List<SlideShow> slideShowList = slideShowMapper.selectList(new EntityWrapper<SlideShow>()
                    .orderBy("createTime", false));

            modelMap.put("success", true);
            modelMap.put("data", slideShowList);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询广告列表时出现异常");
            log.error("(微信)查询广告列表时出现异常" + e.getMessage());
            return modelMap;
        }

    }
}
