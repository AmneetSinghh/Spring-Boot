package com.example.rabbitmq.demo.publisher;


import com.example.rabbitmq.demo.config.RabbitConfig;
import com.example.rabbitmq.demo.dto.Order;
import com.example.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Value("${order.routing-key}")
    public String orderRoutingKey;

    @Value("${order.exchange-name}")
    public String orderExchange;
    @Autowired
    private RabbitTemplate template;


    @PostMapping("/publish/{restaurantName}")
    public String bookOrder(@RequestBody Order order,@PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderstatus = new OrderStatus(order, "PROCESS","order placed successfully in "+ restaurantName);
        template.convertAndSend(orderExchange,orderRoutingKey,orderstatus);
        return "Success !!";
    }
}
