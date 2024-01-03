package com.example.rabbitmq.demo.consumer;

import com.example.rabbitmq.demo.dto.OrderStatus;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderConsumer {

    long minTime = -1;
    long maxTime = -1;

    long minTime2 = -1;
    long maxTime2 = -1;

    /*
    In scale use dynamic registration.
     */

    // create 8 channels
    /*
     * so in round robin fashion rabbbitMQ will send messages to 8 channels..
     *
     */

//    @RabbitListener(queues = "${order.queue-name}",  concurrency = "8")
//    public void ConsumeMessageFromQueue(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }



    /*
     * concurrency =2  means 2 concurrent channels, so prefetch=1 in both channels.
     * default strateg is round robin in concurrency in rabbitMQ
     * setting larger prefetch for a single consumer is beneficial
     * if have multiple consumers means multiple containers then setting little small prefetch otherwise other consumers will get idle.
     */

    @RabbitListener(queues = "order_queue",ackMode = "MANUAL")
    public void ConsumeMessageFromQueue1(OrderStatus status,  Channel channel, Message message) throws InterruptedException, IOException {
        try{
            if(minTime == -1){
                minTime = System.currentTimeMillis();
            }
            System.out.println("message received from queue 1: " +System.currentTimeMillis());
//            Thread.sleep(3000);
            maxTime = System.currentTimeMillis();
            System.out.println("message received from queue 1 "+ (maxTime- minTime));
            // After successful processing, acknowledge the message
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            System.out.println("Delivery tag -> "+ deliveryTag+ " Successfull "+  status.toString());
            channel.basicAck(deliveryTag, false);
        } catch(Exception e){
            // Reject the message and requeue if needed
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicReject(deliveryTag, true);
        }
    }
    // 2715 miliseconds.



//
//    @RabbitListener(queues = "order_queue-2")
//    public void ConsumeMessageFromQueue2(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }
//
//    @RabbitListener(queues = "order_queue-3")
//    public void ConsumeMessageFromQueue3(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }
//
//    @RabbitListener(queues = "order_queue-4")
//    public void ConsumeMessageFromQueue4(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }
//
//    @RabbitListener(queues = "order_queue-5")
//    public void ConsumeMessageFromQueue5(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }
//
//    @RabbitListener(queues = "order_queue-6")
//    public void ConsumeMessageFromQueue6(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }
//
//    @RabbitListener(queues = "order_queue-7")
//    public void ConsumeMessageFromQueue7(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }
//
//    @RabbitListener(queues = "order_queue-8")
//    public void ConsumeMessageFromQueue8(OrderStatus status) throws InterruptedException {
//        if(minTime == -1){
//            minTime = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 1: " +System.currentTimeMillis());
//        Thread.sleep(1000);
//        maxTime = System.currentTimeMillis();
//        System.out.println("message received from queue 1 "+ (maxTime-minTime)/1000L);
//    }


//    @RabbitListener(queues = "${order.queue-name-2}",  concurrency = "3")
//    public void ConsumeMessageFromQueue2(OrderStatus status) throws InterruptedException {
//        if(minTime2 == -1){
//            minTime2 = System.currentTimeMillis();
//        }
//        System.out.println("message received from queue 2: " +System.currentTimeMillis());
//        Thread.sleep(500);
//        maxTime2 = System.currentTimeMillis();
//        System.out.println("message received from queue 2 "+ (maxTime2-minTime2)/1000L);
//    }

//    @RabbitListener(queues = "${order.queue-name}")
//    public void ConsumeMessageFromQueue_2(OrderStatus status) throws InterruptedException {
//        System.out.println("message received from queue 2: "+ status);
//        Thread.sleep(5000);
//    }
//
//    @RabbitListener(queues = "${order.queue-name}")
//    public void ConsumeMessageFromQueue_3(OrderStatus status) throws InterruptedException {
//        System.out.println("message received from queue 3: "+ status);
//        Thread.sleep(5000);
//
//    }
//
//    @RabbitListener(queues = "${order.queue-name}")
//    public void ConsumeMessageFromQueue_4(OrderStatus status) throws InterruptedException {
//        System.out.println("message received from queue 4: "+ status);
//        Thread.sleep(5000);
//    }
}

/*
1. normal queue
2. scale increase - increase concurrency
3. make separate queues


note : For now I am not seeing benefit or compare concurrency = 4 or having multiple queues.
 */









