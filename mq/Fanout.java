/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  Fanout.java   
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
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.yyn.demo.mq.Send.User;

/**    
 *  
 * @author: yuyanan
 * @date:   2018年12月4日      
 */
@Component
public class Fanout
{
  //fanout 
    public static final String fanout_queue1 = "fanout_queue_sendMobile";
    public static final String fanout_queue2 = "fanout_queue_sendEmail";
    public static final String fanout_exchange = "fanout_exchange";
    
    
    @RabbitListener(queues = fanout_queue1)
    public void processMessage1(User content) {
        System.out.println("fanout_111111:----" + content.toString());
    }
    
    @RabbitListener(queues = fanout_queue2)
    public void processMessage2(User content) {
        System.out.println("fanout_222222:----" + content.toString());
    }
    
    
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(fanout_queue1);
    }
 
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(fanout_queue2);
    }
 
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanout_exchange);
    }
 
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
 
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
