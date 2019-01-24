package com.beyondsoft.rdc.cloud.fnb.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * webSocket 处理器
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-18
 */
@Slf4j
public class CustomerHandler implements WebSocketHandler {


    /**
     * 处理消息
     *
     * @param session WebSocketSession
     * @param message WebSocketMessage
     * @throws Exception Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("接收消息：{}", message.getPayload().toString());
    }

    /**
     * 建立连接
     *
     * @param session WebSocketSession
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("连接成功......");
        WebSocketContext.add(session);
    }

    /**
     * 连接出错时
     *
     * @param session WebSocketSession
     * @param exception Throwable
     * @throws Exception Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.error("链接出错，关闭链接......:{}", exception);
        WebSocketContext.remove(session);
    }

    /**
     * 连接关闭时
     *
     * @param session WebSocketSession
     * @param closeStatus CloseStatus
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("链接关闭......" + closeStatus.toString());
        WebSocketContext.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
