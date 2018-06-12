package com.self.litejob.test;

import com.self.litejob.executor.DataflowJob;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
public class MyJob implements DataflowJob<String> {
    @Override
    public List<String> fetchData() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    @Override
    public void processData(List<String> data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("process data" + data + "    " + simpleDateFormat.format(new Date()));
    }
}
