package com.mengzhou.trashrecycling.common.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * webSocket
 *
 * @author ZHOUTONG
 * @date 2019年11月07日 5:40
 */
//在相对路径中发布端点websocket
@ServerEndpoint("/websocket")
@Component
public class WebSocketServlet {

    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServlet> webSocketSet = new CopyOnWriteArraySet<WebSocketServlet>();
    private javax.websocket.Session session = null;

    /**
     * @ClassName: onOpen
     * @Description: 开启连接的操作
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        webSocketSet.add(this);
        System.out.println(webSocketSet);

    }

    /**
     * @ClassName: onClose
     * @Description: 连接关闭的操作
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    /**
     * 发送消息方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发消息方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(String message) throws IOException {
        for (WebSocketServlet myWebSocket : webSocketSet) {
            myWebSocket.sendMessage(message);
        }
    }
}
