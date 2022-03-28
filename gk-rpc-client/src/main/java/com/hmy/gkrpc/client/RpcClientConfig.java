package com.hmy.gkrpc.client;

import com.hmy.gkrpc.Peer;
import com.hmy.gkrpc.codec.Decoder;
import com.hmy.gkrpc.codec.Encoder;
import com.hmy.gkrpc.codec.JsonDecoder;
import com.hmy.gkrpc.codec.JsonEncoder;
import com.hmy.transport.HttpTransportClient;
import com.hmy.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));

    public int helper(String s, int lo, int hi) {
        int cnt = 0;
        int n = s.length();
        s.substring()
        while (lo >= 0 && hi < n && s.charAt(lo) == s.charAt(hi)) {
            cnt += 1;
            lo -= 1;
            hi += 1;
        }
        return cnt;
    }
}
