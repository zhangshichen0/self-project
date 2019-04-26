package com.self.quartz;

import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shichen
 * @create 2019-04-26
 * @desc
 */
public class JobBean implements Job {

    private String param1;
    private String param2;

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(context);

        String jobName = context.getJobDetail().getKey().getName();

        //打印当前的执行时间 例如 2017-11-22 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是："+ sf.format(date) + ", param1 " + param1 + ", param2 " + param2 + ", jobName " + jobName);
        //具体的业务逻辑
        System.out.println("开始生成任务报表 或 开始发送邮件");
    }
}
