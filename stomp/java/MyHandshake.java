package com.fast.member.stomp;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author linyun
 * @date 2018/9/13 下午3:12
 */
@Slf4j
public class MyHandshake implements HandshakeInterceptor {
	
	 private final String SHOP_ID = "shopId";
	    private final String USERNAME = "userName";
	
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String userName = (String) servletRequest.getHeader(USERNAME);
            String shopId = (String) servletRequest.getHeader(SHOP_ID);
            System.out.println("shake:" + userName);
            System.out.println("shake:" + shopId);
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(shopId)) {
            	return true;
            }
            attributes.put(SHOP_ID, shopId);
            attributes.put(USERNAME, userName);
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
