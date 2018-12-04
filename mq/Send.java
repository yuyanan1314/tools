/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  Send.java   
 * @Package com.yyn.demo.mq   
 * @Description:   
 * @author: yuyanan  
 * @date:   2018年12月4日   
 * @version V1.0 
 * @Copyright:  yuyanan
 * 
 */
package com.yyn.demo.mq;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.json.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**    
 *  
 * @author: yuyanan
 * @date:   2018年12月4日      
 */
@Component
@Controller
public class Send
{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private User user = new User("admin", "admin");
    
    @RequestMapping("/send_work")
    @ResponseBody
    public void send(){
//        rabbitTemplate.setReturnCallback(new ReturnCallback()
//        {
//            
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey)
//            {
//                System.out.println("sender return success" + message.toString()+"==="+replyCode+"==="+replyText+"==="+exchange);                
//            }
//        });
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("HelloSender 消息发送成功 ");
            }
        });
        rabbitTemplate.convertAndSend(Work.workQueue, user);

    }
    
    @RequestMapping("/send_fanout")
    @ResponseBody
    public void fanout(){
        rabbitTemplate.convertAndSend(Fanout.fanout_exchange, "", user);
    }
    
    @RequestMapping("/send_direct")
    @ResponseBody
    public void direct(){
        rabbitTemplate.convertAndSend(Direct.DIRECT_EXCHANGE, Direct.ROUTTING_KEY, user);
    }
    
    @RequestMapping("/send_topic")
    @ResponseBody
    public void topic(){
        this.rabbitTemplate.convertAndSend(TopicM.TOPIC_EXCHANGE,"lzc.message", user);
        this.rabbitTemplate.convertAndSend(TopicM.TOPIC_EXCHANGE, "lzc.lzc", user);
    }
    
    @Data
    @AllArgsConstructor
    public static class User implements Serializable {
        private String username;
        private String password;
    }
}
