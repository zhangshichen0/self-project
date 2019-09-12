package com.self.serializable.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/10
 * @desc
 */
public class TestAvroMain {

    public static void main(String[] args) throws IOException {
        Article.Builder builder = Article.newBuilder();
        builder.setId(1);
        builder.setTitle("test");
        Article user = builder.build();

        //序列化
        File diskFile = new File("avroT.avro");
        DatumWriter<Article> userDatumWriter = new SpecificDatumWriter<>(Article.class);
        DataFileWriter<Article> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        //指定schema
        dataFileWriter.create(Article.getClassSchema(), diskFile);
        dataFileWriter.append(user);
        dataFileWriter.fSync();//多次写入之后，可以调用fsync将数据同步写入磁盘(IO)通道
        user.setId(2);
        user.setTitle("test2");
        dataFileWriter.append(user);
        dataFileWriter.close();
        System.out.println(diskFile.length());

        //反序列化
        DatumReader<Article> userDatumReader = new SpecificDatumReader<>(Article.class);
        // 也可以使用DataFileStream
        // DataFileStream<Article> dataFileStream = new DataFileStream<>(new FileInputStream(diskFile),userDatumReader);
        DataFileReader<Article> dataFileReader = new DataFileReader<>(diskFile, userDatumReader);
        Article _current = null;
        while (dataFileReader.hasNext()) {

            //注意:avro为了提升性能，_current对象只会被创建一次，且每次遍历都会重用此对象
            //next方法只是给_current对象的各个属性赋值，而不是重新new。
            _current = dataFileReader.next(_current);
            //toString方法被重写，将获得JSON格式
            System.out.println(_current);
        }
        dataFileReader.close();
    }

}
