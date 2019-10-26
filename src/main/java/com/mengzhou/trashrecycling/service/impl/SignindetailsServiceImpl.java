package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Signindetails;
import com.mengzhou.trashrecycling.mapper.SignindetailsMapper;
import com.mengzhou.trashrecycling.service.SignindetailsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-10-26
 */
@Service
public class SignindetailsServiceImpl extends ServiceImpl<SignindetailsMapper, Signindetails> implements SignindetailsService {

    @Autowired
    private SignindetailsMapper signindetailsMapper;

    @Override
    public Signindetails findByOpenId(String openId) {
        return signindetailsMapper.findByOpenId(openId);
    }
}
