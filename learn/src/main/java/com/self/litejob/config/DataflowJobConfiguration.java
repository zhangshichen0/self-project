package com.self.litejob.config;

import lombok.*;

/**
 * @author shichen
 * @create 2018/6/12
 * @desc
 */
@RequiredArgsConstructor
@Getter
public final class DataflowJobConfiguration implements JobTypeConfiguration{

    private final JobCoreConfiguration jobCoreConfiguration;

    private final JobType jobType = JobType.DATAFLOW;

    private final String jobClass;
}
