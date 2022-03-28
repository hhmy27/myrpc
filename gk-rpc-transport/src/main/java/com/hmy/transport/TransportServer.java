package com.hmy.transport;

/**
 * 启动、监听端口
 * 接受请求
 * 关闭监听
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start();
    void stop();
}
