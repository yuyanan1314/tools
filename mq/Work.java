/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  Work.java   
 * @Package com.yyn.demo.mq   
 * @Description:   
 * @author: yuyanan  
 * @date:   2018年12月4日   
 * @version V1.0 
 * @Copyright:  yuyanan
 * 
 */
package com.yyn.demo.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.yyn.demo.mq.Send.User;

/**    
 *  
 * @author: yuyanan
 * @date:   2018年12月4日      
 */
@Configuration
public class Work
{

    //Work
    public static final String workQueue = "work_queue_polling";
    
    @Bean
    public Queue workQueue1() {
        return new Queue(workQueue);
    }
    
    @RabbitListener(queues = workQueue)
    public void processMessage1(String content) {
        System.out.println("work_polling_11111:----"+content);
    }
    
    @RabbitListener(queues = workQueue)
    public void processMessage2(String content) {
        System.out.println("work_polling_22222----"+content);
    }
}
