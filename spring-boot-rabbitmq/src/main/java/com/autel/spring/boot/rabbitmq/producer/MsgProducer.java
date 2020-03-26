package com.autel.spring.boot.rabbitmq.producer;

import com.autel.spring.boot.rabbitmq.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 消息的生产者
 * @author A20019
 * @date 2020/3/26
 */

@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgProducer.class);

    private RabbitTemplate template;

    /**
     * 构造方法注入 RabbitTemplate
     * @param template
     */
    @Autowired
    public MsgProducer(RabbitTemplate template){
        this.template = template;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        template.setConfirmCallback(this);
    }

    /**
     *  把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
     * @param content
     */
    public void sendMsg(String content){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        template.convertAndSend(RabbitConfig.EXCHANGE_A ,RabbitConfig .ROUTINGKEY_A , content ,correlationData);
    }

    /**
     *  回调
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("回调ID： " + correlationData);
        if(ack){
            LOGGER.info("消息成功消费。。。");
        }else{
            LOGGER.info("消息消费失败： " + cause);
        }
    }
}
