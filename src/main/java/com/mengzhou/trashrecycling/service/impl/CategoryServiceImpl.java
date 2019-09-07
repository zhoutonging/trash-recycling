package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.model.Category;
import com.mengzhou.trashrecycling.mapper.CategoryMapper;
import com.mengzhou.trashrecycling.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void save(Category category) {

    }

    @Override
    public void removeById(Integer id) {

    }

    @Override
    public void modifyById(Integer id) {

    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public List<Category> findAll() {

        if (redisUtil.exists("categoryList")) {
            List<Category> categoryList = (List<Category>) redisUtil.get("categoryList");
            return categoryList;
        }

        List<Category> categoryList = categoryMapper.selectList(new EntityWrapper<Category>().orderBy("createTime", false));
        redisUtil.set("categoryList", categoryList);

        return categoryList;
    }
}
