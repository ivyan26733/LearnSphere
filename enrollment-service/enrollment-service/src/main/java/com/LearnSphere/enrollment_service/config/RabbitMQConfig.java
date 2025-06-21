package com.LearnSphere.enrollment_service.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
//    public static final String USER_SERVICE_QUEUE = "user.delete.user.queue";
    public static final String ENROLLMENT_SERVICE_QUEUE = "user.delete.enrollment.queue";
    public static final String EXCHANGE = "user.exchange";

//    @Bean
//    public Queue queue(){
//        return new Queue(ENROLLMENT_SERVICE_QUEUE);
//    } //Creating queue

    @Bean
    public Queue Enrollmentqueue(){
        return new Queue(ENROLLMENT_SERVICE_QUEUE);
    } //Creating queue

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding Userbinding(Queue Enrollmentqueue, DirectExchange exchange){
        return BindingBuilder.bind(Enrollmentqueue).to(exchange).with("user.delete");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
