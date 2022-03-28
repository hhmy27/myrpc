package com.hmy.gkrpc.client;

import com.hmy.gkrpc.Peer;
import com.hmy.gkrpc.Request;
import com.hmy.gkrpc.Response;
import com.hmy.gkrpc.ServiceDescriptor;
import com.hmy.gkrpc.codec.Decoder;
import com.hmy.gkrpc.codec.Encoder;
import com.hmy.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 创建一个请求对象
        Request request = new Request();
        // 设置你要调用的方法，参数信息
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParams(args);
        // 发起远程调用
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote" + response);
        }
        // 获取数据
        return response.getData();
    }


    private Response invokeRemote(Request request) {
        // 根据request建立链接，发起远程调用，返回Response
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] bytes = this.encoder.encoder(request);
            InputStream in = client.write(new ByteArrayInputStream(bytes));
            // 获得远程调用的返回结果，反序列化，并且解析
            byte[] inBytes = IOUtils.readFully(in, in.available(), true);
            response = this.decoder.decoder(inBytes, Response.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setCode(-1);
            response.setMessage("RpcClient got error:" + e.getClass() + ":" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }

}

