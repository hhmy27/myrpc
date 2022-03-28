package com.hmy.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @Description
 */
public class JsonEncoder implements  Encoder{
    @Override
    public byte[] encoder(Object obj) {
        return JSON.toJSONBytes(obj);
    }

}
