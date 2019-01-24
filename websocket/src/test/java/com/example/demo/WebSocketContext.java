package com.beyondsoft.rdc.cloud.fnb.websocket;

import com.alibaba.fastjson.JSON;
import com.beyondsoft.rdc.cloud.fnb.common.utils.Inspect;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocket容器
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-18
 */
@Slf4j
public class WebSocketContext {

    /**
     * key websocket-shopId-username
     */
    private static final String format = "websocket-%s-%s";

    /**
     * 容器 key: format
     */
    private static List<SocketOnlineSession> context = Collections.synchronizedList(new ArrayList<>(50));


    /**
     * 添加
     *
     * @param session WebSocketSession
     */
    public static void add(WebSocketSession session) {
        SocketOnlineSession onlineSession = getSocketOnlineSession(session);
        context.add(onlineSession);
    }

    /**
     * 移除
     *
     * @param session WebSocketSession
     */
    public static void remove(WebSocketSession session) {
        SocketOnlineSession onlineSession = getSocketOnlineSession(session);
        context.remove(onlineSession);
    }

    private static void send(TextMessage message, WebSocketSession socketSession) {
        try {
            socketSession.sendMessage(message);
        } catch (IOException e) {
            log.error("发送错误:", e);
            e.printStackTrace();
        }
    }

    /**
     * 给所有在线用户发送消息 - 广播
     *
     * @param socketResult SocketResult
     */
    public static void sendMessageToAll(SocketResult socketResult) {
        log.info("socketResult:{}", socketResult.toString());
        try {
            TextMessage textMessage = new TextMessage(JSON.toJSONString(socketResult));
            sendMessageToAll(textMessage);
        } catch (Exception e) {
            log.error("socketToAll error:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * 给一个门店所有在线设备发信息
     *
     * @param shopId shopId
     * @param resultConsumer SocketResult
     */
    public static void sendMessageToShop(Consumer<SocketResult> resultConsumer, Integer shopId) {
        SocketResult socketResult = new SocketResult();
        resultConsumer.accept(socketResult);
        sendMessageToShop(socketResult, shopId);
    }

    /**
     * 给一个门店所有在线设备发信息
     *
     * @param shopId shopId
     * @param socketResult SocketResult
     */
    public static void sendMessageToShop(SocketResult socketResult, Integer shopId) {
        try {
            TextMessage textMessage = new TextMessage(JSON.toJSONString(socketResult));
            sendMessageToShop(textMessage, shopId);
        } catch (Exception e) {
            log.error("socketToUser error:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * 给某个用户发送消息 - 点对点
     *
     * @param shopId 门店id
     * @param username 帐号
     * @param socketResult SocketResult
     */
    public static void sendMessageToUser(SocketResult socketResult, Integer shopId, String username) {
        log.info("socketResult:{}", socketResult.toString());
        try {
            TextMessage textMessage = new TextMessage(JSON.toJSONString(socketResult));
            sendMessageToUser(textMessage, shopId, username);
        } catch (Exception e) {
            log.error("socketToUser error:{}", e);
            e.printStackTrace();
        }

    }

    /**
     * 给所有在线用户发送消息 - 广播
     */
    private static void sendMessageToAll(TextMessage message) {
        Inspect.verifyNull(message);
        for (SocketOnlineSession socketSession : context) {
            send(message, socketSession.getSession());
        }
    }

    /**
     * 给某个门店所有在线设备发送消息
     *
     * @param message TextMessage
     * @param shopId 门店id
     */
    private static void sendMessageToShop(TextMessage message, Integer shopId) {
        Inspect.verifyNull(message);
        Inspect.verifyNull(shopId);
        context.stream()
               .filter(obj -> obj.getShopId().equals(shopId))
               .forEach(obj -> send(message, obj.getSession()));
    }

    /**
     * 给某个用户发送消息 - 点对点
     *
     * @param message TextMessage
     * @param shopId 门店id
     * @param username 帐号
     */
    private static void sendMessageToUser(TextMessage message, Integer shopId, String username) {
        Inspect.verifyNull(message);
        Inspect.verifyNull(shopId);
        Inspect.verifyNull(username);
        context.stream()
               .filter(obj -> obj.getShopId().equals(shopId) && obj.getUsername().equals(username))
               .forEach(obj -> send(message, obj.getSession()));
    }

    private static SocketOnlineSession getSocketOnlineSession(WebSocketSession session) {
        String userName = (String) session.getAttributes().get(CustomHandshakeInteceptor.USERNAME);
        String shopId = (String) session.getAttributes().get(CustomHandshakeInteceptor.SHOP_ID);
        return new SocketOnlineSession(session, Integer.parseInt(shopId), userName);
    }

}
