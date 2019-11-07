package com.mengzhou.trashrecycling.common.websocket;

/**
 * @author ZHOUTONG
 * @date 2019年11月07日 6:11
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.io.Serializable;

/**
 * webSocket配置 否则springboot无法加载导致连接失败
 *
 * @author xiaoshuaishuai
 * @date 2019-05-21 11:18
 */
@Configuration
public class WebSocketConfig implements Serializable {

    private static final long serialVersionUID = 7600559593733357846L;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
