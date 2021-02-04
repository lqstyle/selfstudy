package com.example.demo1.serial.compareserial;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author liangqing
 * @since 2020/11/24 16:10
 */
public class KryoUtils {
    public byte[] serialize(Object obj) {
        Kryo kryo = kryos.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeClassAndObject(output, obj);
        output.close();
        return byteArrayOutputStream.toByteArray();
    }

    public <T> T deserialize(byte[] bytes) {
        Kryo kryo = kryos.get();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        input.close();
        return (T) kryo.readClassAndObject(input);
    }


    private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            return kryo;
        }

    };


    public static void main(String[] args) {
        KryoUtils kryoUtils = new KryoUtils();
        for (int i = 0; i < 1000; i++) {
            User user = new User("1233", 1, "idea");
            byte[] bytes = kryoUtils.serialize(user);
            User user1 = kryoUtils.deserialize(bytes);
            System.out.println(user1.toString());
        }
    }
}
