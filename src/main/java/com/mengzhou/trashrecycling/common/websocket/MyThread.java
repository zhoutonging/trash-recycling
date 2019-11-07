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

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        //sum = adminUserMapper.selectCount(new EntityWrapper<Adminuser>());
        WebSocketServlet wbs = new WebSocketServlet();
        //while (stopMe) {
        //new_sum = adminUserMapper.selectCount(new EntityWrapper<Adminuser>());
        //if (sum != new_sum) {
        //    System.out.println("change");
        //    sum = new_sum;
        wbs.onMessage(sum);
        //}
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    // TODO Auto-generated catch block
        //    e.printStackTrace();
        //}
        //}
    }
}