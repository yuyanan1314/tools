package com.beyondsoft.rdc.cloud.fnb.stomp;

import com.beyondsoft.rdc.cloud.fnb.common.utils.Inspect;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;

/**
 *
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-22
 */
@Data
@Slf4j
public class SocketTool {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private SimpUserRegistry userRegistry;

    /**
     * 点对点发送
     * 最终订阅地址：/user/shopId-name/path
     *
     * @param shopId 门店id
     * @param name 用户名
     * @param path 订阅地址
     * @param response 数据
     */
    public void sendToUser( Integer shopId, String name, String path, SocketResponse response){
        try {
            Inspect.verifyNull(shopId);
            Inspect.verifyNull(name);
            Inspect.verifyNull(path);
            WebSocketUser user = new WebSocketUser(name, shopId.toString());
            template.convertAndSendToUser(user.getName(), path, response.getData());
        }catch (Exception e) {
            log.warn("发送失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 广播发送
     *
     * 最终订阅地址：/path
     *
     * @param path 订阅地址
     * @param response 数据
     */
    public void sendToAll(String path, SocketResponse response){
        try {
            Inspect.verifyNull(path);
            template.convertAndSend(path, response.getData());
        }catch (Exception e) {
            log.warn("发送失败", e);
            e.printStackTrace();
        }
    }
}
