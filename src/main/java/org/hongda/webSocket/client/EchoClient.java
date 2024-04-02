package org.hongda.webSocket.client;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * @ClassName EchoClient
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/29 14:52
 **/
@ClientEndpoint
public class EchoClient {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket 连接已经建立。");
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到服务器消息：" + message);
    }

    @OnClose
    public void onClose() {
        System.out.println("WebSocket 连接已经关闭。");
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("WebSocket 连接出现错误：" + t.getMessage());
    }

    public void connect(String url) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, new URI(url));
    }

    public void send(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void close() throws IOException {
        session.close();
    }
}
