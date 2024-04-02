package org.hongda.webSocket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @ClassName WebSocketServer
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/29 14:26
 **/
@ServerEndpoint("/echo")
@Slf4j
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        log.info("WebSocketServer 连接成功..");
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端消息: {}", message);
        session.getAsyncRemote().sendText("服务器收到消息：" + message);
    }

    @OnClose
    public void onClose() {
        log.info("WebSocketServer 连接断开..");
    }

    @OnError
    public void onError(Throwable error) {
        log.error("WebSocketServer 发生错误", error.getMessage());
    }
}
