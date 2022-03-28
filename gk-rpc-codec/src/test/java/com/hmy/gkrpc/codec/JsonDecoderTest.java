package com.hmy.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest {

    @Test
    public void decoder() {
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean("a", 1);
        byte[] bytes = encoder.encoder(bean);
        JsonDecoder jsonDecoder = new JsonDecoder();
        TestBean bean1 = jsonDecoder.decoder(bytes, TestBean.class);
        assertEquals(bean.getAge(), bean1.getAge());
        assertEquals(bean.getName(), bean1.getName());
    }
}