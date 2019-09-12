package com.self.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shichen
 * @create 2019-04-26
 * @desc
 */
public class PrintCurrentTimeJob implements IJob{

    @Override
    public void process() {
        //打印当前的执行时间 例如 2017-11-22 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sf.format(date));
    }
}
