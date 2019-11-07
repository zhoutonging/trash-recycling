package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Cyclopedia;
import com.mengzhou.trashrecycling.mapper.CyclopediaMapper;
import com.mengzhou.trashrecycling.service.CyclopediaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-11-03
 */
@Service
@Slf4j
public class CyclopediaServiceImpl extends ServiceImpl<CyclopediaMapper, Cyclopedia> implements CyclopediaService {

    @Autowired
    private CyclopediaMapper cyclopediaMapper;

    @Override
    public LayuiResult save(Cyclopedia cyclopedia) {
        try {
            if (cyclopedia.getCyclopediaContent() == null) {
                return LayuiResult.fail("环保百科内容为空");
            }
            cyclopedia.setCreateTime(new Date());
            cyclopediaMapper.insert(cyclopedia);
            return LayuiResult.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("添加出现异常");
        }
    }

    @Override
    public LayuiResult deleteById(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }
            cyclopediaMapper.deleteById(id);
            return LayuiResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("删除出现异常");
        }
    }

    @Override
    public LayuiResult modifyById(Cyclopedia cyclopedia) {
        try {
            if (cyclopedia.getCyclopediaContent() == null || cyclopedia.getId() == null) {
                return LayuiResult.fail("必要参数为空");
            }
            cyclopediaMapper.updateById(cyclopedia);
            return LayuiResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("修改出现异常");
        }
    }

    @Override
    public LayuiResult findById(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }
            Cyclopedia cyclopedia = cyclopediaMapper.selectById(id);
            return LayuiResult.success(cyclopedia);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("查询出现异常");
        }
    }

    @Override
    public LayuiResult findAll(Integer page, Integer limit, String cyclopediaName) {
        try {
            PageHelper.startPage(page, limit);
            List<Cyclopedia> cyclopediaList;

            if (cyclopediaName == null || cyclopediaName.equals("")) {
                cyclopediaList = cyclopediaMapper.selectList(new EntityWrapper<Cyclopedia>()
                        .orderBy("createTime", false));
            } else {
                cyclopediaList = cyclopediaMapper.selectList(new EntityWrapper<Cyclopedia>()
                        .orderBy("createTime", false).like("cyclopediaName", cyclopediaName));
            }

            if (cyclopediaList == null) {
                return LayuiResult.success(null);
            }

            PageInfo pageInfo = new PageInfo(cyclopediaList);

            return LayuiResult.success(pageInfo.getTotal(), cyclopediaList);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("查询失败");
        }
    }

    @Override
    public Map<String, Object> findAllWechat() {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            List<Cyclopedia> cyclopediaList =cyclopediaMapper.selectList(new EntityWrapper<Cyclopedia>()
                    .orderBy("createTime", false));

            modelMap.put("success", true);
            modelMap.put("data", cyclopediaList);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            log.error("(微信)查询环保百科出现异常:"+e.getMessage());
            modelMap.put("success", false);
            modelMap.put("msg", "查询失败");
            return modelMap;
        }

    }

    @Override
    public Map<String, Object> findByIdWechat(Integer id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            Cyclopedia cyclopedia = cyclopediaMapper.selectById(id);

            modelMap.put("success", true);
            modelMap.put("data", cyclopedia);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            log.error("(微信)根据id查询环保百科出现异常:"+e.getMessage());
            modelMap.put("success", false);
            modelMap.put("msg", "查询失败");
            return modelMap;
        }

    }
}
