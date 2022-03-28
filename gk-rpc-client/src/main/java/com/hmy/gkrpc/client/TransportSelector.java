package com.hmy.gkrpc.client;

import com.hmy.gkrpc.Peer;
import com.hmy.transport.TransportClient;

import java.util.List;

/**
 * 选择指定Server链接
 */
public interface TransportSelector {
    /**
     *
     * @param peers 可以连接的server端信息
     * @param count client与server建立多少个链接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    // 选择一个transport与server交互
    TransportClient select();

    // 释放用完的client
    void release(TransportClient client);

    void close();
}
