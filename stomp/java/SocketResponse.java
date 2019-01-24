package com.beyondsoft.rdc.cloud.fnb.stomp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * socket 返回值
 *
 * @author yuyanan01@beyondsoft.com
 * @version 1.0
 * @date 19-1-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketResponse<T> {

    /**
     * 数据对象
     */
    private T data;
}
