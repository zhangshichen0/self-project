package com.self.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * 测试fastjson1.2.60之前版本遇到\x后引起oom问题
 *
 * @author shichen
 * @create 2019-10-14
 * @desc
 */
public class V1246OomBugTestMain {

    public static void main(String[] args) {
        JSON.parse("[{\"a\":\"a\\x]");
    }

}
