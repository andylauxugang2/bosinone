package com.guangbei.common.serializer;

import java.nio.charset.Charset;

/**
 * Created by xugang on 2017/3/1.
 */
public class StringSerializer implements Serializer<String> {
    private final Charset charset = Charset.forName("UTF8");

    @Override
    public byte[] serialize(String value) {
        return (value == null ? null : value.getBytes(charset));
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
