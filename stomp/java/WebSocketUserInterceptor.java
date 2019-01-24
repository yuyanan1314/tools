package com.beyondsoft.rdc.cloud.fnb.stomp;


import java.util.LinkedList;
import java.util.Map;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * websocket连接通道拦截器
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-22
 */
public class WebSocketUserInterceptor implements ChannelInterceptor {

    private final String SHOP_ID = "shopId";
    private final String USERNAME = "userName";

    /**
     * 获取包含在stomp中的用户信息
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                Object name = ((Map) raw).get(USERNAME);
                Object shopId = ((Map) raw).get(SHOP_ID);
                if (name instanceof LinkedList) {
                    // 设置当前访问器的认证用户
                    String uname = ((LinkedList) name).get(0).toString();
                    String ushop = ((LinkedList) shopId).get(0).toString();
                    WebSocketUser webSocketUser = new WebSocketUser(uname, ushop);
                    accessor.setUser(webSocketUser);
                }
            }
        }
        return message;
    }


}
