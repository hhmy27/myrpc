package com.hmy.gkrpc.client;

import com.hmy.gkrpc.codec.Decoder;
import com.hmy.gkrpc.codec.Encoder;
import com.hmy.gkrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @Description 调用远程服务的代理类
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());
        this.selector.init(this.config.getServers(), this.config.getConnectCount(), this.config.getTransportClass());
    }
    public <T> T getProxy(Class clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector));
    }
}
