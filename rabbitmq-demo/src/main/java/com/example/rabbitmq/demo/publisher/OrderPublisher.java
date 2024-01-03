package com.example.rabbitmq.demo.publisher;

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

    @Value("${order.routing-key-2}")
    public String orderRoutingKey2;

    @Value("${order.exchange-name}")
    public String orderExchange;
    @Autowired
    private RabbitTemplate template;


    @PostMapping("/publish/{restaurantName}")
    public String bookOrder(@RequestBody Order order,@PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        for(int i=1;i<=3000;i++){
            OrderStatus orderstatus = new OrderStatus(order, "PROCESS","order placed successfully in "+ restaurantName+i);
            template.convertAndSend(orderExchange,orderRoutingKey,orderstatus);
//            for(int j=2;j<=8;j++){
//                template.convertAndSend(orderExchange,orderRoutingKey+"-"+j,orderstatus);
//            }

        }
        return "Success !!";
    }

    // 49 seconds , single consumer, prefetch = 250 unlimited. all processed 1 million.
    // 50 seconds , single consumer, prefetch = 1, only 60k processed.


//    @PostMapping("/publish2/{restaurantName}")
//    public String bookOrder2(@RequestBody Order order,@PathVariable String restaurantName){
//        order.setOrderId(UUID.randomUUID().toString());
//        OrderStatus orderstatus = new OrderStatus(order, "PROCESS","order placed successfully in "+ restaurantName);
//        for(int i=1;i<=100;i++){
//            template.convertAndSend(orderExchange,orderRoutingKey2,orderstatus);
//        }
//        return "Success !!";
//    }

}
