package com.autel.spring.boot.rabbitmq.receiver;

import com.autel.spring.boot.rabbitmq.config.RabbitConfig;
import com.autel.spring.boot.rabbitmq.producer.MsgProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author A20019
 * @date 2020/3/26
 */

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitHandler
    public void process(String content){
        LOGGER.info("接收到队列A的消息： " + content);
    }
}
