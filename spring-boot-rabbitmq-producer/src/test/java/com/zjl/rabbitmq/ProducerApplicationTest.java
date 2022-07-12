package com.zjl.rabbitmq;

import com.zjl.rabbitmq.config.RabbitMQConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/3/6 17:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTest{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        rabbitTemplate.convertAndSend("aa", "item.insert", "商品新增，routing key 为item.insert");
        rabbitTemplate.convertAndSend("aa", "item.delete", "商品删除，routing key 为item.delete");
        rabbitTemplate.convertAndSend("aa", "item.delete", "商品删除，routing key 为item.delete");
    }

}