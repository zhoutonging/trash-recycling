package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.History;
import com.mengzhou.trashrecycling.mapper.HistoryMapper;
import com.mengzhou.trashrecycling.service.HistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-11-17
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {


    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void save(History history) {
        historyMapper.insert(history);
    }

    @Override
    public Map<String, Object> find7Count() {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            List<Map<String, Object>> mapList = historyMapper.find7Count();
            modelMap.put("success", true);
            modelMap.put("data", mapList);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", true);
            modelMap.put("msg", "查询失败");
            return modelMap;
        }

    }

    @Override
    public History findByOpenId(String openId) {
        return historyMapper.findByOpenId(openId);
    }
}
