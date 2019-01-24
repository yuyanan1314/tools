package com.beyondsoft.rdc.cloud.fnb.stomp;


import java.security.Principal;
import lombok.AllArgsConstructor;

/**
 * webSocketUser对象
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-22
 */
@AllArgsConstructor
public final class WebSocketUser implements Principal {

    public static final String split = "-";

    /**
     * 帐号
     */
    private  String userName;

    /**
     * 门店id
     */
    private  String shopId;


    public String getShopId() {
        return shopId;
    }

    /**
     * 区别用户: 门店id-帐号
     * @return String
     */
    @Override
    public String getName() {
        return shopId + split + userName;
    }


}
