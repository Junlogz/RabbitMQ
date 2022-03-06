package com.zjl.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/3/6 10:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-rabbitmq.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void queueTest(){
        rabbitTemplate.convertAndSend("spring_queue", "只发队列spring_queue的消息");
    }

    @Test
    public void fanoutTest(){
        /**
         * 参数1：交换机名称
         * 参数2：路由键名（广播设置为空）
         * 参数3：发送的消息内容
         */
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "发送到spring_fanout_exchange交换机的广播消息");
    }

    /**
     * 通配符
     * 交换机类型为 topic
     * 匹配路由键的通配符，*表示一个单词，#表示多个单词
     * 绑定到该交换机的匹配队列能够收到对应消息
     */
    @Test
    public void topicTest(){
        /**
         * 参数1：交换机名称
         * 参数2：路由键名
         * 参数3：发送的消息内容
         */
        rabbitTemplate.convertAndSend("spring_topic_exchange", "lxs.bj", "发送到spring_topic_exchange交换机lxs.bj的消息");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "lxs.bj.1", "发送到spring_topic_exchange交换机lxs.bj.1的消息");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "lxs.bj.2", "发送到spring_topic_exchange交换机lxs.bj.2的消息");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "xzk.cn", "发送到spring_topic_exchange交换机xzk.cn的消息");
    }

}
