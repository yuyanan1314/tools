/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  Direct.java   
 * @Package com.yyn.demo.mq   
 * @Description:   
 * @author: yuyanan  
 * @date:   2018年12月4日   
 * @version V1.0 
 * @Copyright:  yuyanan
 * 
 */
package com.yyn.demo.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yyn.demo.mq.Send.User;

/**    
 *  
 * @author: yuyanan
 * @date:   2018年12月4日      
 */
@Configuration
public class Direct
{
    //redirect模式
    public static final String DIRECT_QUEUE1 = "direct.queue1";
    public static final String DIRECT_QUEUE2 = "direct.queue2";
    public static final String DIRECT_EXCHANGE = "direct.exchange";
    public static final String ROUTTING_KEY = "direct.yyn";
    
    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE1);
    }
    
    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE2);
    }
 
 
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    
 //     * 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(ROUTTING_KEY);
    }
    
    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(ROUTTING_KEY);
    }
    
 // queues是指要监听的队列的名字
    @RabbitListener(queues = DIRECT_QUEUE1)
    public void receiveDirect1(User user) {
 
        System.out.println("【receiveDirect1监听到消息】" + user);
    }
 
    @RabbitListener(queues = DIRECT_QUEUE2)
    public void receiveDirect2(User user) {
 
        System.out.println("【receiveDirect2监听到消息】" + user);
    }

}
