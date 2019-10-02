package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.model.Recruitment;
import com.mengzhou.trashrecycling.mapper.RecruitmentMapper;
import com.mengzhou.trashrecycling.service.RecruitmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-09-22
 */
@Service
@Slf4j
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Override
    public LayuiResult save(Recruitment recruitment) {
        try {
            if (recruitment.getRecruitmentName() == null || recruitment.getRecruitmentContent() == null
                    || recruitment.getContacts() == null) {
                return LayuiResult.fail("添加失败,必填项不能为空");
            }
            recruitment.setCreateTime(new Date());
            recruitmentMapper.insert(recruitment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加招聘信息时出现错误" + e.getMessage());
            return LayuiResult.fail("添加失败");
        }
        return LayuiResult.success("添加成功");
    }

    @Override
    public LayuiResult removeById(Integer Id) {
        try {
            if (Id == null) {
                return LayuiResult.fail("id为空");
            }
            recruitmentMapper.deleteById(Id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除招聘信息时出现错误" + e.getMessage());
            return LayuiResult.fail("删除失败");
        }
        return LayuiResult.success("删除成功");
    }

    @Override
    public LayuiResult modifyById(Recruitment recruitment) {

        try {
            recruitmentMapper.updateById(recruitment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新招聘信息时出现错误" + e.getMessage());
            return LayuiResult.fail("更新失败");
        }
        return LayuiResult.success("更新成功");
    }

    @Override
    public Recruitment findById(Integer Id) {
        return recruitmentMapper.selectById(Id);
    }

    @Override
    public List<Recruitment> findAll(String recruitmentName) {
        if (recruitmentName != null) {
            return recruitmentMapper.selectList(new EntityWrapper<Recruitment>()
                    .like("recruitmentName", recruitmentName));
        }
        return recruitmentMapper.selectList(new EntityWrapper<Recruitment>()
                .orderBy("createTime", false));
    }
}
