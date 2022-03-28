package com.hmy.transport;

import com.hmy.gkrpc.Peer;

import java.io.InputStream;


/**
 * @Description
 * 创建数据
 * 发送数据并且等待响应
 * 关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data);
    void close();
}
