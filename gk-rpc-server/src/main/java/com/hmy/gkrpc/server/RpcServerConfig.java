package com.hmy.gkrpc.server;

import com.hmy.gkrpc.codec.Decoder;
import com.hmy.gkrpc.codec.Encoder;
import com.hmy.gkrpc.codec.JsonDecoder;
import com.hmy.gkrpc.codec.JsonEncoder;
import com.hmy.transport.HttpTransportServer;
import com.hmy.transport.TransportServer;
import lombok.Data;

/**
 * @Description server配置
 */
@Data
public class RpcServerConfig {
    // 网路协议
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    // 序列化相关
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private final int port = 3000;
}
