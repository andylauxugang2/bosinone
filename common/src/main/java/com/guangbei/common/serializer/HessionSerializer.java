package com.guangbei.common.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by xugang on 2017/3/1.
 */
public class HessionSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(Object object) {
        if (object == null)
            return null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
        Hessian2Output out = new Hessian2Output(bos);
        try {
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes) {

        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        Hessian2Input in = new Hessian2Input(bin);
        try {
            Object o = in.readObject(Object.class);
            return o;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
