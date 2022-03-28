package com.hmy.gkrpc;

import lombok.Data;

/**
 * @Description rpc的响应
 */

@Data
public class Response {
    // 服务返回编码，0表示成功，1表示失败
    private int code = 0;
    // 具体的错误信息
    private String message = "ok";
    // 返回的数据
    private Object data;
}
