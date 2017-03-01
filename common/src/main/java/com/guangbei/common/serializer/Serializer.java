package com.guangbei.common.serializer;

/**
 * Created by xugang on 2017/3/1.
 */
public interface Serializer<T> {
    byte[] serialize(T t);

    T deserialize(byte[] bytes);
}
