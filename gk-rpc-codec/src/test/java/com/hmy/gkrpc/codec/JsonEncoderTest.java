package com.hmy.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest {

    @Test
    public void encoder() {
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean("a", 1);
        byte[] bytes = encoder.encoder(bean);
        assertNotNull(bytes);
    }
}