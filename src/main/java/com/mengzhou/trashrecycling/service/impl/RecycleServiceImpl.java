package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.Dto.RecycleDto;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.model.Recycle;
import com.mengzhou.trashrecycling.mapper.RecycleMapper;
import com.mengzhou.trashrecycling.service.RecycleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
 * @since 2019-10-12
 */
@Service
@Slf4j
public class RecycleServiceImpl extends ServiceImpl<RecycleMapper, Recycle> implements RecycleService {

    @Autowired
    private RecycleMapper recycleMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> save(Recycle recycle, String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (recycle.getAddressId() == null || recycle.getRecycleCategoryId() == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "收货地址或垃圾类别为空");
                log.error("(微信)添加垃圾回收信息时出错:收货地址或垃圾类别为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "(微信)添加垃圾回收信息时出现错误,sessionKey已过期");
                log.error("(微信)添加垃圾回收信息时出现错误,sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            recycle.setCreateTime(new Date());
            recycle.setOpenId(openId);
            recycleMapper.insert(recycle);

            modelMap.put("success", true);
            modelMap.put("msg", "添加成功");

            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加垃圾回收信息时出现异常");
            log.error("(微信)添加垃圾回收信息时出现异常:" + e.getMessage());
            return modelMap;
        }
    }

    @Override
    public LayuiResult deleteById(Integer id) {
        if (id == null) {
            return LayuiResult.fail("id为空");
        }
        recycleMapper.deleteById(id);
        return LayuiResult.success("删除成功");
    }

    @Override
    public Map<String, Object> modifyById(Recycle recycle) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (recycle.getId() == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "(微信)修改垃圾回收信息时出现错误,id为空");
                log.error("(微信)修改垃圾回收信息时出现错误,id为空");
                return modelMap;
            }
            recycleMapper.updateById(recycle);
            modelMap.put("success", true);
            modelMap.put("msg", "修改成功");
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "(微信)修改垃圾回收信息时出现异常");
            log.error("(微信)修改垃圾回收信息时出现异常:" + e.getMessage());

            return modelMap;
        }

    }

    @Override
    public LayuiResult findById(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }

            Recycle recycle = recycleMapper.selectById(id);
            return LayuiResult.success(recycle);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查询信息时出现异常:" + e.getMessage());
            return LayuiResult.fail("根据id查询信息时出现异常");
        }
    }

    @Override
    public Map<String, Object> findByOpenId(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "(微信)根据openId查询垃圾回收信息时出现错误,sessionKey为空");
                log.error("(微信)根据openId查询垃圾回收信息时出现错误,sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "(微信)根据openId查询垃圾回收信息时出现错误,sessionKey已过期");
                log.error("(微信)根据openId查询垃圾回收信息时出现错误,sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            List<Recycle> recycleList = recycleMapper.selectList(new EntityWrapper<Recycle>()
                    .eq("openId", openId));

            modelMap.put("success", true);
            modelMap.put("data", recycleList);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "根据openId查询信息时出现异常");
            log.error("根据openId查询信息时出现异常:" + e.getMessage());
            return modelMap;
        }

    }

    @Override
    public List<Recycle> findAll() {
        List<Recycle> recycleList = recycleMapper.selectList(new EntityWrapper<Recycle>());
        return recycleList;
    }

    @Override
    public List<RecycleDto> findAllJOIN(Integer id) {
        return recycleMapper.findAll(id);
    }
}
