package com.beyondsoft.rdc.cloud.fnb.mq;

import com.beyondsoft.rdc.cloud.fnb.stomp.SocketTool;
import com.beyondsoft.rdc.cloud.fnb.stomp.WebSocketConfig;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * webSocket stomp 配置
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-22
 */
@Configuration
@EnableWebSocketMessageBroker
public class CustomWebSocketConfig extends WebSocketConfig{

    /**
     * webSocket工具类
     *
     * @return SocketTool
     */
    @Bean
    public SocketTool socketTool(){
        SocketTool socketTool = new SocketTool();
        return socketTool;
    }

}
