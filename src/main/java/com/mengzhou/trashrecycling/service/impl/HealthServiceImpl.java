package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Health;
import com.mengzhou.trashrecycling.mapper.HealthMapper;
import com.mengzhou.trashrecycling.service.HealthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-10-15
 */
@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {

}
