///**  
// * All rights Reserved, Designed By www.tydic.com
// * @Title:  RabbitConfig.java   
// * @Package com.yyn.demo.mq   
// * @Description:   
// * @author: yuyanan  
// * @date:   2018年12月4日   
// * @version V1.0 
// * @Copyright:  yuyanan
// * 
// */
//package com.yyn.demo.mq;
//
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**    
// *  
// * @author: yuyanan
// * @date:   2018年12月4日      
// */
//@Configuration
//public class RabbitConfig
//{
//    @Autowired
//    public ConnectionFactory connectionFactory;
// 
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setConcurrentConsumers(3);
//        factory.setMaxConcurrentConsumers(10);
//        return factory;
//    }
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
// 
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(jsonMessageConverter());
//        return template;
//    }
//
//}
