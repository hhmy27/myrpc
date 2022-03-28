package com.hmy.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @Description
 */
public class JsonDecoder implements Decoder {

    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
