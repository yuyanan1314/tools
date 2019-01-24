package com.beyondsoft.rdc.cloud.fnb.websocket;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 握手拦截器
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-18
 */
@Slf4j
public class CustomHandshakeInteceptor implements HandshakeInterceptor {

    public static final String SHOP_ID = "shopId";
    public static final String  USERNAME = "userName";

    /**
     * 握手之前
     * @param serverHttpRequest ServerHttpRequest
     * @param serverHttpResponse ServerHttpResponse
     * @param webSocketHandler WebSocketHandler
     * @param attributes Map
     * @return true 握手成功  false 握手拒绝
     * @throws Exception Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        log.info("握手之前....");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String shopId = servletRequest.getParameter(SHOP_ID);
            String userName = servletRequest.getParameter(USERNAME);
            if (StringUtils.isBlank(shopId) || StringUtils.isBlank(userName)) {
                log.warn("握手拒绝 shopId:{}  userName:{}", shopId, userName);
                return false;
            }
            attributes.put("shopId", shopId);
            attributes.put("userName", userName);
            return true;
        }
        return false;
    }

    /**
     * 握手之后
     * @param serverHttpRequest ServerHttpRequest
     * @param serverHttpResponse ServerHttpResponse
     * @param webSocketHandler WebSocketHandler
     * @param e Exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
        log.info("握手之后....");
    }
}
