package com.example.rabbitmq.demo.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${order.queue-name}")
    public String orderQueue;

    @Value("${order.queue-name-2}")
    public String orderQueue2;

    @Value("${order.exchange-name}")
    public String orderExchange;

    @Value("${order.routing-key}")
    public String orderRoutingKey;

    @Value("${order.routing-key-2}")
    public String orderRoutingKey2;


    @Bean
    public Queue queue(){
        System.out.println("orderQueue name "+ orderQueue);
        return new Queue(orderQueue);
    }

    @Bean
    public Queue queue2(){
        System.out.println("orderQueue name "+ orderQueue2);
        return new Queue(orderQueue2);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(orderExchange);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange).
                with(orderRoutingKey);
    }

    @Bean
    public Binding binding1(Queue queue2, TopicExchange exchange){
        return BindingBuilder
                .bind(queue2)
                .to(exchange).
                with(orderRoutingKey2);
    }

    @Bean
    public MessageConverter converter(){
        // we want the message to be in json format, when published or consumer
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(converter());
        factory.setPrefetchCount(30); // Set the desired prefetch count here
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }


}
