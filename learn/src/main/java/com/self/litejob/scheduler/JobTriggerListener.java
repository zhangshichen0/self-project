package com.self.litejob.scheduler;

import lombok.RequiredArgsConstructor;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@RequiredArgsConstructor
public final class JobTriggerListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        if (null != trigger.getPreviousFireTime()) {
            //TODO 触发任务错过
            System.out.println("任务错过" + trigger.getKey().getName());
        }
    }
}
