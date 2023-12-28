package com.example.cacheservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/*
1. try code with not using TTL.
2. try code with TTL
*/

@Component
public class CacheBreakdown {



    @Autowired
    private RedisService redisService;
    private final int SIZE = 50;
    int blockThreadCount = 0;

    private String cacheBreakDown(String key){
        String data = redisService.get(key);
        if(data!=null){
            return data;
        }
        System.out.println(Thread.currentThread().getName()+" Enters here");
        if(acquireLock(key,Thread.currentThread().getName())){
            try {
                System.out.println(Thread.currentThread().getName() + " Enter into lock db query");
                sleep(2);// get from db and save to redis.
                redisService.set(key,"Blog is successfully saved into redis");
            } finally {
                releaseLock(key,Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+ " Lock is released");
            }
        }
        else{
            sleep(1);/// all threads to wait.
            cacheBreakDown(key);// again same function
        }
        return redisService.get(key);
    }


    /* expiry TTL, means thread which is doing db call blocked or db down */
    private String cacheBreakDownWithTTL(String key){
        String data = redisService.get(key);
        if(data!=null){
            return data;
        }
        System.out.println(Thread.currentThread().getName()+" Enters here");
        if(acquireLockWithTTL(key,Thread.currentThread().getName())){
            try {
                System.out.println(Thread.currentThread().getName() + " Enter into lock db query");
                if(blockThreadCount<1){
                    System.out.println("Count of blockThreadCount: "+ blockThreadCount);
                    blockThreadCount++;
                    sleep(100);// BLOCKED or INFINITE waiting.
                }
                System.out.println(Thread.currentThread().getName() + " Enters after sleep");
                redisService.set(key,"Blog is successfully saved into redis");
            } finally {
                releaseLock(key,Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+ " Lock is released");
            }
        }
        else{
            sleep1(1);/// all threads to wait.
            cacheBreakDownWithTTL(key);// again same function
        }
        return redisService.get(key);
    }

    private static void sleep(int timeout) {
        try {
            System.out.println(Thread.currentThread().getName() + " Enters under sleep");
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void sleep1(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean acquireLockWithTTL(String key, String value){
        // Usage:
        Duration expiry = Duration.ofSeconds(4);
        Boolean lock = redisService.setNx(lockKey(key),value,expiry);
        if(lock){
            System.out.println("Lock is aquired by key : "+ key +" value : "+ value);
            return true;
        }
        return false;
    }

    private Boolean acquireLock(String key, String value){
        Boolean lock = redisService.setNx(lockKey(key),value);
        if(lock){
            System.out.println("Lock is aquired by key : "+ key +" value : "+ value);
            return true;
        }
        return false;
    }

    private void releaseLock(String key, String value){
        String lockClient = redisService.get(lockKey(key));
        System.out.println("release lock : "+ value +" -> "+ lockClient);
        if(lockClient!=null && lockClient.equals(value)){ // this is needed, because we need to verify same client is release their lock.
            redisService.deleteData(lockKey(key));
            System.out.println("Lock is released by key : "+ key +" value : "+ value);
        }
        else{
            System.out.println("Error releasing lock by key : "+ key +" value : "+ value);
        }
    }

    private String lockKey(String key){
        return "lock-"+ key;
    }

    public void setUp(){
        redisService.deleteData("blog_123");
        Thread[] threads = new Thread[SIZE];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(() -> {
                String blog = cacheBreakDownWithTTL("blog_123");
                System.out.println(Thread.currentThread().getName()+" blog get "+ blog);
            });
        }

        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }

    }
}


/*
 If we didn’t had the check of value==client then the lock which was acquired by
 new client would have been released by the old client, allowing other clients to
 lock the resource and process simultaneously along with second client, causing race
 conditions or data corruption, which is undesired
 */