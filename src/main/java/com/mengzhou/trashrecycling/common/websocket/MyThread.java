package com.mengzhou.trashrecycling.common.websocket;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.mapper.AdminUserMapper;
import com.mengzhou.trashrecycling.model.Adminuser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 对数据库的访问
 *
 * @author ZHOUTONG
 * @date 2019年11月07日 5:41
 */
public class MyThread implements Runnable {

    @Autowired
    private AdminUserMapper adminUserMapper;

    private int sum;
    private int new_sum;
    private boolean stopMe = true;

    public void stopMe() {
        stopMe = false;
    }

    public void run() {
        WebSocketServlet wbs = new WebSocketServlet();
        //TODO 这个是往前端发送消息
        /**
         * 使用方法：在使用的地方先注入WebSocketServlet
         * 然后使用wbs.onMessage(sum);向前端发送状态码
         */
        //wbs.onMessage(sum);
    }
}