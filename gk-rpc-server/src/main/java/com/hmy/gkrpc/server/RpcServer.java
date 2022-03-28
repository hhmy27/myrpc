package com.hmy.gkrpc.server;

import com.hmy.gkrpc.Request;
import com.hmy.gkrpc.Response;
import com.hmy.gkrpc.codec.Decoder;
import com.hmy.gkrpc.codec.Encoder;
import com.hmy.gkrpc.common.utils.ReflectionUtils;
import com.hmy.transport.RequestHandler;
import com.hmy.transport.TransportServer;
import com.sun.deploy.util.ReflectionUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Struct;

/**
 * @Description
 */
@Data
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream in, OutputStream out) {
            Response response = new Response();
            try {
                byte[] bytes = new byte[in.available()];
                IOUtils.readFully(in, bytes);
                // 反序列化
                Request request = RpcServer.this.decoder.decoder(bytes, Request.class);
                // 根据request去查找
                ServiceInstance instance = serviceManager.lookup(request);
                Object data = serviceInvoker.invoke(instance, request);
                response.setData(data);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
                response.setCode(-1);
                response.setMessage("RpcServer error: " + e.getMessage());
            } finally {
                byte[] bytes = RpcServer.this.encoder.encoder(response);
                try {
                    out.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }
}
