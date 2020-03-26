package com.autel.springbootschedule.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 并行多线程任务功能（方式二）
 *
 * 通过异步方式执行调度任务
 * 配置Application入口的@EnableAsync，在定时任务方法前面配置@Async，即配置了任务线程池
 *
 * @author A20019
 * @date 2020/3/26
 */

@Component
public class ScheduledTasksAsync {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasksAsync.class);

    @Async
    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void test3() {
        LOGGER.info("i am test4 ，每5秒执行一次");
    }

}
