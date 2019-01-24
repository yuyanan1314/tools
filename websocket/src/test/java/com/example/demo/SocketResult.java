package com.beyondsoft.rdc.cloud.fnb.websocket;

import java.io.Serializable;
import lombok.Data;

/**
 * webSocket 返回值
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-18
 */
@Data
public class SocketResult implements Serializable {

    /**
     * 结果类型
     */
    private TypeEnum type;

    /**
     * 结果数据
     */
    private Object data;

    public static SocketResult build(TypeEnum typeEnum, Object data){
        SocketResult result = new SocketResult();
        result.setType(typeEnum);
        result.setData(data);
        return result;
    }

    /**
     * 结果类型
     */
    public enum  TypeEnum {
        MEAL("socket-cashier-meal", "套餐更新");

        private String name;
        private String desc;

        TypeEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        TypeEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }
    }
}
