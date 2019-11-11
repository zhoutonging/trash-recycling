package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.common.Dto.CommunityDto;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.common.websocket.WebSocketServlet;
import com.mengzhou.trashrecycling.model.Community;
import com.mengzhou.trashrecycling.mapper.CommunityMapper;
import com.mengzhou.trashrecycling.service.CommunityService;
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
 * @since 2019-11-09
 */
@Service
@Slf4j
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WebSocketServlet webSocketServlet;

    @Override
    public Map<String, Object> save(Community community, String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey为空");
                log.error("(微信)添加社区动态出现错误:sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey已过期");
                log.error("(微信)添加社区动态出现错误:sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            community.setOpenId(openId);
            community.setStatus(1); //默认状态是未审核状态
            community.setCreateTime(new Date());
            communityMapper.insert(community);

            // webSocket通知后台有新动态需要审核
            webSocketServlet.sendMessageAll("3");
            modelMap.put("success", true);
            modelMap.put("msg", "发布成功,等待审核");
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加社区动态出现异常");
            log.error("(微信)添加社区动态出现异常:" + e.getMessage());
            return modelMap;
        }
    }

    @Override
    public Map<String, Object> deleteByIdWechar(Integer id, String sessionKey) {
        return null;
    }

    @Override
    public LayuiResult deleteById(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }
            communityMapper.deleteById(id);
            return LayuiResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("删除失败");

        }
    }

    @Override
    public LayuiResult modifyByStatus(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }
            Community community = new Community();
            community.setStatus(0); //修改成审核通过的状态
            community.setId(id);

            communityMapper.updateById(community);
            return LayuiResult.success("更改状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("更改状态失败");
        }
    }

    @Override
    public Map<String, Object> findAllWechar() {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            List<CommunityDto> communityDtos = communityMapper.findAll();//查询已通过审核的动态
            modelMap.put("success", true);
            modelMap.put("data", communityDtos);
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询所有社区动态出现异常");
            log.error("(微信)查询所有社区动态出现异常:" + e.getMessage());
            return modelMap;
        }
    }

    @Override
    public LayuiResult findAll(Integer page, Integer limit) {
        try {
            PageHelper.startPage(page, limit);
            List<Community> communityDtos = communityMapper.selectList(new EntityWrapper<Community>()
                    .orderBy("createTime", false));
            PageInfo pageInfo = new PageInfo(communityDtos);

            return LayuiResult.success(pageInfo.getTotal(), communityDtos);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询所有社区动态出现异常:" + e.getMessage());
            return LayuiResult.fail("查询所有社区动态出现异常");
        }
    }

    @Override
    public LayuiResult findById(Integer id) {
        return null;
    }
}
