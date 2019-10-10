package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.model.Recyclecategory;
import com.mengzhou.trashrecycling.mapper.RecyclecategoryMapper;
import com.mengzhou.trashrecycling.service.RecyclecategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
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
 * @since 2019-10-05
 */
@Service
@Slf4j
public class RecyclecategoryServiceImpl extends ServiceImpl<RecyclecategoryMapper, Recyclecategory> implements RecyclecategoryService {

    @Autowired
    private RecyclecategoryMapper recyclecategoryMapper;

    @Override
    public LayuiResult save(Recyclecategory recyclecategory) {
        try {
            if (recyclecategory.getRecycleCategoryName() == null || recyclecategory.getRecycleCategoryName().equals("")) {
                log.error("添加垃圾回收类别时出现异常:垃圾回收类别名称为空");
                return LayuiResult.fail("添加失败");
            }
            recyclecategory.setCreateTime(new Date());
            recyclecategoryMapper.insert(recyclecategory);
            return LayuiResult.success("添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加垃圾回收类别时出现异常:" + e.getMessage());
            return LayuiResult.fail("添加失败");
        }

    }

    @Override
    public LayuiResult modifyById(Recyclecategory recyclecategory) {
        try {
            if (recyclecategory.getId() == null || recyclecategory.getId().equals("")) {
                log.error("修改垃圾回收类别时出现异常:垃圾回收类别名称或Id为空");
                return LayuiResult.fail("更新失败");
            }
            recyclecategoryMapper.updateById(recyclecategory);
            return LayuiResult.success("更新成功");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改垃圾回收类别时出现异常:" + e.getMessage());
            return LayuiResult.fail("更新失败");
        }

    }

    @Override
    public LayuiResult deleteById(Integer id) {
        try {
            if (id == null) {
                log.error("删除垃圾回收类别时出现异常:垃圾回收类别Id为空");
                return LayuiResult.fail("删除失败");
            }
            recyclecategoryMapper.deleteById(id);
            return LayuiResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除垃圾回收类别时出现异常:" + e.getMessage());
            return LayuiResult.fail("删除失败");
        }

    }

    @Override
    public LayuiResult findById(Integer id) {
        try {
            if (id == null) {
                log.error("查询垃圾回收类别时出现异常:垃圾回收类别Id为空");
                return LayuiResult.fail("查询失败");
            }
            Recyclecategory recyclecategory = recyclecategoryMapper.selectById(id);

            return LayuiResult.success(recyclecategory);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询垃圾回收类别时出现异常:" + e.getMessage());
            return LayuiResult.fail("查询失败");
        }

    }

    @Override
    public List<Recyclecategory> findAll() {
        List<Recyclecategory> recyclecategoryList = recyclecategoryMapper.selectList(new EntityWrapper<Recyclecategory>()
                .orderBy("createTime", false));
        return recyclecategoryList;
    }

    @Override
    public Map<String, Object> findAllWechar() {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            List<Recyclecategory> recyclecategoryList = recyclecategoryMapper.selectList(new EntityWrapper<Recyclecategory>()
                    .orderBy("createTime", false));
            modelMap.put("success", true);
            modelMap.put("data", recyclecategoryList);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", true);
            modelMap.put("data", "查询失败");
            log.error("(微信)查询垃圾类别时出错" + e.getMessage());
            return modelMap;
        }
    }
}
