package com.self.springmvc.test;

import com.self.http.HttpUtils;
import com.self.springmvc.model.UserProto;
import okhttp3.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shichen
 * @create 2018/5/9
 * @desc
 */
public class ProtobufTest {

    public static void main(String[] args) {
        UserProto.UserPackage userPackage = UserProto.UserPackage.newBuilder().setId(1).setName("zhangshichen").setAge(20).build();

        Map<String, String> headerMap = new HashMap<>(1);
        headerMap.put("Content-Type", "application/x-protobuf");
        MediaType mediaType = MediaType.parse("Content-Type:application/x-protobuf");
        String result = HttpUtils.post("http://127.0.0.1:8080/serializable/protobuf/insert", headerMap, mediaType, userPackage.toByteArray());
        System.out.println(result);
    }

}
