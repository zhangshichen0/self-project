package com.self.serializable.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestXmlJaxbMain {

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1);
        article.setTitle("test");
        article.setAge(10);
        Article.count = 20;

        FileWriter writer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(article.getClass());
            Marshaller marshal = context.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //输出到控制台
            marshal.marshal(article, System.out);

            //输出到文件
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            writer = new FileWriter("jaxbxml.xml");
            marshal.marshal(article, writer);
            marshal.marshal(article, byteArrayOutputStream);

            System.out.println("object:" + article + ", 序列化后大小：" + byteArrayOutputStream.toByteArray().length);
            writer.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshal = context.createUnmarshaller();
            FileInputStream fis = new FileInputStream("jaxbxml.xml");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            Article _article = (Article) unmarshal.unmarshal(isr);
            System.out.println(_article);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
