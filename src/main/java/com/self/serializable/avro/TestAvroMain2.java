package com.self.serializable.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接通过读取定义的schema,序列化
 *
 * @author shichen
 * @create 2018/5/10
 * @desc
 */
public class TestAvroMain2 {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = TestAvroMain2.class.getClassLoader().getResourceAsStream("avro/article.avsc");
        Schema schema = new Schema.Parser().parse(inputStream);

        GenericRecord article = new GenericData.Record(schema);
        article.put("id", 1);
        article.put("title", "test");

        List<GenericRecord> list = new ArrayList<>();
        list.add(article);

        System.out.println(avroToJson(jsonToAvro("{\"id\": 1, \"title\": \"test\"}", schema)));

        //序列化
        serializeAvroToFile(list, schema, "avro2.avro");

        //反序列化
        deserializeAvroFromFile(schema, "avro2.avro");
    }

    /**
     * 序列化到文件
     *
     * @param list
     * @param fileName
     * @throws IOException
     */
    public static void serializeAvroToFile(List<GenericRecord> list, Schema schema, String fileName) throws IOException {
        DatumWriter<GenericRecord> userDatumWriter = new SpecificDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(userDatumWriter);

        File diskFile = new File(fileName);
        long length = diskFile.length();

        if (length == 0) {
            //如果是新文件，则插入Schema
            dataFileWriter.create(schema, diskFile);
        } else {
            //对于现有文件，则直接追加到文件的尾部
            dataFileWriter.appendTo(diskFile);
        }
        for (GenericRecord user : list) {
            dataFileWriter.append(user);
        }
        dataFileWriter.close();
    }

    /**
     * 从文件中反序列化
     *
     * @param fileName
     * @throws IOException
     */
    public static void deserializeAvroFromFile(Schema schema, String fileName) throws IOException {
        File file = new File(fileName);
        DatumReader<GenericRecord> userDatumReader = new SpecificDatumReader<>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, userDatumReader);
        //System.out.println(dataFileReader.getSchema().toString(true)); 可以从数据文件中获取schema
        GenericRecord user = null;
        System.out.println("----------------deserializeAvroFromFile-------------------");
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }

    /**
     * 序列化为二进制
     *
     * @param userList
     * @return
     * @throws IOException
     */
    public static byte[] serializeAvroToByteArray(List<GenericRecord> userList) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> userDatumWriter = new SpecificDatumWriter<>(GenericRecord.class);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(userList.get(0).getSchema(), baos);
        for (GenericRecord user : userList) {
            dataFileWriter.append(user);
        }
        dataFileWriter.close();
        return baos.toByteArray();
    }

    /**
     * 二进制反序列化为对象
     *
     * @param usersByteArray
     * @throws IOException
     */
    public static void deserialzeAvroFromByteArray(byte[] usersByteArray) throws IOException {
        SeekableByteArrayInput sbai = new SeekableByteArrayInput(usersByteArray);
        DatumReader<GenericRecord> userDatumReader = new SpecificDatumReader<>(GenericRecord.class);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(sbai, userDatumReader);
        System.out.println("----------------deserialzeAvroFromByteArray-------------------");
        GenericRecord readUser = null;
        while (dataFileReader.hasNext()) {
            readUser = dataFileReader.next(readUser);
            System.out.println(readUser);
        }
    }

    public static byte[] jsonToAvro(String json, Schema schema) throws IOException {
        InputStream input = null;
        DataFileWriter<GenericRecord> writer = null;
        Encoder encoder = null;
        ByteArrayOutputStream output = null;
        try {
            DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);
            input = new ByteArrayInputStream(json.getBytes());
            output = new ByteArrayOutputStream();
            DataInputStream din = new DataInputStream(input);
            writer = new DataFileWriter<>(new GenericDatumWriter<>());
            writer.create(schema, output);
            Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
            GenericRecord datum;
            while (true) {
                try {
                    datum = reader.read(null, decoder);
                } catch (EOFException eofe) {
                    break;
                }
                writer.append(datum);
            }
            writer.flush();
            return output.toByteArray();
        } finally {
            try {
                input.close();
            } catch (Exception e) {
            }
        }
    }

    public static String avroToJson(byte[] avro) throws IOException {
        boolean pretty = false;
        GenericDatumReader<GenericRecord> reader = null;
        JsonEncoder encoder = null;
        ByteArrayOutputStream output = null;
        try {
            reader = new GenericDatumReader<>();
            InputStream input = new ByteArrayInputStream(avro);
            DataFileStream<GenericRecord> streamReader = new DataFileStream<>(input, reader);
            output = new ByteArrayOutputStream();
            Schema schema = streamReader.getSchema();
            DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
            encoder = EncoderFactory.get().jsonEncoder(schema, output, pretty);
            for (GenericRecord datum : streamReader) {
                writer.write(datum, encoder);
            }
            encoder.flush();
            output.flush();
            return new String(output.toByteArray());
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
