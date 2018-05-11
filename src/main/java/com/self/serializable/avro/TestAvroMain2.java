package com.self.serializable.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 直接通过读取定义的schema,序列化
 * @author shichen
 * @create 2018/5/10
 * @desc
 */
public class TestAvroMain2 {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = TestAvroMain2.class.getClassLoader().getResourceAsStream("avro/article.avsc");
        Schema schema = new Schema.Parser().parse(inputStream);

        GenericRecord article = new GenericData.Record(schema);
        article.put("id",  1);
        article.put("title", "test");

        File diskFile = new File("avro2.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, diskFile);
        dataFileWriter.append(article);
        dataFileWriter.close();

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(diskFile, datumReader);
        GenericRecord _current = null;
        while (dataFileReader.hasNext()) {
            _current = dataFileReader.next(_current);
            System.out.println(_current);
        }

        dataFileReader.close();
    }

}
