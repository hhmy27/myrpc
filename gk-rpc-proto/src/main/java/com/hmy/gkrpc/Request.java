package com.hmy.gkrpc;

import lombok.Data;

/**
 * @Description 表示RPC的一个请求
 */

@Data
public class Request {
    private ServiceDescriptor serviceDescriptor;
    private Object[] params;
}
