package com.beyondsoft.rdc.cloud.fnb.stomp;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * webSocket stomp 配置
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-22
 */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 配置监听规则
     *
     * @param config MessageBrokerRegistry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 订阅Broker名称
        config.enableSimpleBroker("/user", "/topic");
        //全局前缀
        //config.setApplicationDestinationPrefixes("/app/");
        //点对点前缀
        config.setUserDestinationPrefix("/user");
    }

    /**
     * 配置连接地址
     * @param registry StompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		//使用http://ip:port/socket/webSocketServer/sockJs 连接
        registry.addEndpoint("/socket/webSocketServer/sockJs").setAllowedOrigins("*").withSockJS();
		//使用ws://ip:port/socket/webSocketServer/sockJs 连接
        registry.addEndpoint("/socket/webSocketServer").setAllowedOrigins("*");
    }

    /**
     * 注册拦截器
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(createUserInterceptor());
    }

    /**
     * 注册拦截器
     */
    @Bean
    public WebSocketUserInterceptor createUserInterceptor() {
        return new WebSocketUserInterceptor();
    }


}
