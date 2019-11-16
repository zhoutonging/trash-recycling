package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.common.Dto.OrdersDto;
import com.mengzhou.trashrecycling.common.enums.OrderStatusEnum;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.common.websocket.WebSocketServlet;
import com.mengzhou.trashrecycling.mapper.OrdersMapper;
import com.mengzhou.trashrecycling.model.Integraldetails;
import com.mengzhou.trashrecycling.model.Orders;
import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.model.User;
import com.mengzhou.trashrecycling.service.IntegraldetailsService;
import com.mengzhou.trashrecycling.service.OrdersService;
import com.mengzhou.trashrecycling.service.ProductService;
import com.mengzhou.trashrecycling.service.UserService;
import com.mengzhou.trashrecycling.utils.GenerateNum;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-09-07
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private IntegraldetailsService integraldetailsService;

    @Autowired
    private WebSocketServlet webSocketServlet;

    @Override
    public Map<String, Object> save(Integer productId, Orders orders, String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            //判断商品id和商品数量是否为空
            if (productId == null || orders.getProductCount() == null) {
                modelMap.put("msg", "商品id或者商品数量为空");
                modelMap.put("success", false);
                log.error("添加订单时出现错误:商品id或者商品数量为空");
                return modelMap;
            }

            // 查询商品信息
            Product product = productService.selectById(productId);
            if (product == null) {
                modelMap.put("msg", "无法查询到商品");
                modelMap.put("success", false);
                log.error("添加订单时出现错误:无法查询到商品");
                return modelMap;
            }

            //判断sessionKey是否过期
            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("msg", "sessionKey已过期");
                modelMap.put("success", false);
                log.error("添加订单时出现错误:sessionKey已过期");
                return modelMap;
            }

            //拿到缓存中的openId
            String openId = redisUtil.get(sessionKey).toString();

            User user = userService.findByOpenId(openId);
            if (user == null) {
                modelMap.put("msg", "用户不存在");
                modelMap.put("success", false);
                log.error("添加订单时出现错误:用户不存在");
                return modelMap;
            }

            //计算总金额
            int totalMoney = product.getProductPrice() * orders.getProductCount();

            //判断用户是否有足够的积分
            if (totalMoney > user.getIntegral()) {
                modelMap.put("msg", "没有足够的积分哦~");
                modelMap.put("success", true);
                log.error("添加订单时出现错误:用户没有足够的积分");
                return modelMap;
            }

            //更新用户积分
            user.setIntegral(user.getIntegral() - totalMoney);
            userService.updateById(user);

            //添加订单
            orders.setId(GenerateNum.getInstance().GenerateOrder());
            orders.setProductIcon(product.getProductIcon());
            orders.setCreateTime(new Date());
            orders.setProductName(product.getProductName());
            orders.setProductPrice(product.getProductPrice());
            orders.setIntegral(totalMoney);
            orders.setStatus(OrderStatusEnum.SUCCESS.getCode());
            orders.setOpenId(openId);

            webSocketServlet.sendMessageAll("1");  //通知后台有新订单
            ordersMapper.insert(orders);

            //积分详情
            Integraldetails integraldetails = new Integraldetails();
            integraldetails.setIntegralName("购买商品");
            integraldetails.setCreateTime(new Date());
            integraldetails.setOpenId(openId);
            integraldetails.setIntegral("-" + totalMoney);
            integraldetailsService.insert(integraldetails);

            modelMap.put("msg", "购买成功");
            modelMap.put("success", true);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("msg", "新增订单时出现异常");
            modelMap.put("success", false);
            log.error("(微信)新增订单时出现异常" + e.getMessage());
            return modelMap;
        }

    }

    @Override
    public LayuiResult deleteById(String id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }
            ordersMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("删除失败");

        }

        return LayuiResult.success("删除成功");
    }

    @Override
    public Map<String, Object> findByOpenId(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey为空");
                log.error("(微信)根据openId查询订单出现错误:sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey已过期");
                log.error("(微信)根据openId查询订单出现错误:sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                    .eq("openId", openId).orderBy("createTime", false));

            List<OrdersDto> ordersDtoList = new ArrayList<>();
            for (Orders orders : ordersList) {
                OrdersDto ordersDto = new OrdersDto();
                BeanUtils.copyProperties(orders, ordersDto);
                ordersDtoList.add(ordersDto);
            }

            modelMap.put("success", true);
            modelMap.put("data", ordersDtoList);
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询出现异常");
            log.error("(微信)根据openId查询订单出现异常:" + e.getMessage());
            return modelMap;
        }

    }

    @Override
    public Map<String, Object> findCountByOpenId(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey为空");
                log.error("(微信)根据openId查询兑换订单次数出现错误:sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey已过期");
                log.error("(微信)根据openId查询兑换订单次数出现错误:sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                    .eq("openId", openId));


            modelMap.put("success", true);
            modelMap.put("data", ordersList.size());
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询出现异常");
            log.error("(微信)根据openId查询兑换订单次数出现异常:" + e.getMessage());
            return modelMap;
        }
    }

    @Override
    public List<Orders> findAll(String id, Integer status) {

        //根据订单号模糊查询
        if (id != null && !id.equals("")) {
            List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                    .orderBy("createTime", false).like("id", id));
            return ordersList;

        }

        //根据订单状态查询订单
        if (status != null && !status.equals("")) {
            List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                    .orderBy("createTime", false).eq("status", status));
            return ordersList;

        }

        //根据订单id和订单状态查询信息
        if (id != null && !id.equals("") && status != null) {
            List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                    .orderBy("createTime", false).like("id", id).eq("status", status));
            return ordersList;
        }

        //查询订单
        List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                .orderBy("createTime", false));


        return ordersList;
    }

    @Override
    public Map<String, Object> find7Count() {
        Map<String, Object> modelMap = new HashMap<>(16);

        List<Map<String, Object>> mapList = ordersMapper.find7Count();
        modelMap.put("success", true);
        modelMap.put("data", mapList);

        return modelMap;
    }

    @Override
    public Map<String, Object> findOrderById(String id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        if (id == null) {
            modelMap.put("success", false);
            modelMap.put("msg", "id为空");
            return modelMap;
        }

        List<Map<String, Object>> mapList = ordersMapper.findOrderById(id);
        modelMap.put("success", true);
        modelMap.put("data", mapList);

        return modelMap;
    }
}
