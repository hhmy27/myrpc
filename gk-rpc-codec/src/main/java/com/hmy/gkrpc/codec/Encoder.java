package com.hmy.gkrpc.codec;

/**
 * @Description 序列化一个对象变为字节流
 */
public interface Encoder {
    byte[] encoder(Object obj);
}
