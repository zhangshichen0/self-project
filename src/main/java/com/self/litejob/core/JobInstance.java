package com.self.litejob.core;

import com.google.common.base.Joiner;
import com.self.litejob.utils.IpUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.management.ManagementFactory;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = "jobInstanceId")
public final class JobInstance {

    private static final String DELIMITER = "@-@";

    private final String jobInstanceId;

    public JobInstance() {
        jobInstanceId = Joiner.on(DELIMITER).join(IpUtils.getIp(), ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
    }

    /**
     * 获取ip
     *
     * @return
     */
    public String getIp() {
        return jobInstanceId.substring(0, jobInstanceId.indexOf(DELIMITER));
    }
}
