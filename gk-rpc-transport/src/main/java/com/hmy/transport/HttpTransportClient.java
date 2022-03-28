package com.hmy.transport;

import com.hmy.gkrpc.Peer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 基于HTTP的客户端：短链接
 */
@Slf4j
public class HttpTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        log.info("URL: {}", this.url);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(this.url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.connect();
            IOUtils.copy(data, connection.getOutputStream());
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            } else {
                return connection.getErrorStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {

    }
}
