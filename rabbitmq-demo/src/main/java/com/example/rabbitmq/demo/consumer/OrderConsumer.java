package com.example.rabbitmq.demo.consumer;

import com.example.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @RabbitListener(queues = "${order.queue-name}")
    public void ConsumeMessageFromQueue(OrderStatus status){
        System.out.println("message received from queue: "+ status);
    }
}
