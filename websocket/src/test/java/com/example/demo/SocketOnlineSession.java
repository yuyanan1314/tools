package com.beyondsoft.rdc.cloud.fnb.websocket;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * webSocket 在线对象
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-21
 */
@Data
@AllArgsConstructor
public  class SocketOnlineSession {

    /**
     * WebSocketSession
     */
    private WebSocketSession session;

    /**
     * shopId
     */
    private Integer shopId;

    /**
     * userName
     */
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SocketOnlineSession that = (SocketOnlineSession) o;
        return Objects.equals(shopId, that.shopId) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shopId, username);
    }
}
