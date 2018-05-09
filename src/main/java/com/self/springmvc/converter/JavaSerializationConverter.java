package com.self.springmvc.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author shichen
 * @create 2018/5/9
 * @desc
 */
public class JavaSerializationConverter extends AbstractHttpMessageConverter<Serializable>{


    public JavaSerializationConverter() {
        //构造方法中指明consumes（req）和produces（resp）的类型，指明这个类型才会使用这个converter
        super(new MediaType("application", "x-java-serialization", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 使用Serializable，这里可以直接返回true
        // 使用object，这里还要加上Serializable接口实现类判断
        // 根据自己的业务需求加上其他判断
        return true;
    }

    @Override
    protected Serializable readInternal(Class<? extends Serializable> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        byte[] bytes = StreamUtils.copyToByteArray(inputMessage.getBody());
        // base64使得二进制数据可视化，便于测试
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(bytes));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Serializable object = null;
        try {
            object = (Serializable) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    protected void writeInternal(Serializable t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(t);
        // base64使得二进制数据可视化，便于测试
        outputMessage.getBody().write(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
    }
}
