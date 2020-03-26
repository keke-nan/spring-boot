package com.autel.springbootschedule.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author A20019
 * @date 2020/3/26
 */

@Component
public class ScheduledTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * initialDelay : 表示一个初始延迟时间，第一次被调用前延迟的时间
     *  fixedDelay : 表示一个固定延迟时间执行，上个任务完成后,延迟多久执行
     *
     */
    @Scheduled(initialDelay = 1000,fixedDelay = 5000)
    public void test1(){
        LOGGER.info("启动立即执行，i am test1 ，每 5 秒执行一次 ...");
    }


    //Cron表达式用法：秒 分 时 日 月 周

    //固定时间才执行，即为10秒的整数倍执行，比如20秒，30秒，40秒时，会执行
    //每5秒执行一次：     */5 * * * * *
    //每30分钟执行一次：  * */30 * * * *
    //每1小时执行一次：   * * */1 * * *
    //每天2点执行一次：   * * 2 * * *
    @Scheduled(cron = "*/10 * * * * *")
    public void test2() {
        LOGGER.info("i am test2 ，每10秒执行一次...");
    }
}
