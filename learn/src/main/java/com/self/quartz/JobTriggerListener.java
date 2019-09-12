package com.self.quartz;

import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shichen
 * @create 2019-04-26
 * @desc
 */
public class JobTriggerListener extends TriggerListenerSupport {

    @Override
    public void triggerMisfired(Trigger trigger) {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("任务错失执行时间：" + trigger.getJobKey().getName() + "  " + sf.format(date));
    }

    @Override
    public String getName() {
        return "JobTriggerListener";
    }
}
