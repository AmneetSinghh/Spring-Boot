package com.saga.orchestration.service.ratelimiter.strategy;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketLimiter{

    int maxSize;
    int operations;
    int fixedInterval;
    ScheduledExecutorService executorService;
    Queue<Integer> userRequest;

    public LeakyBucketLimiter(int operations, int fixedInterval, int maxSize){ // pick 4 elements at every 2 second.
        this.operations = operations;
        this.fixedInterval = fixedInterval;
        this.maxSize = maxSize;
        userRequest = new ArrayDeque<>();
        executorService = Executors.newSingleThreadScheduledExecutor();
        refillAtFixedRate();
    }


    public boolean isRequestAllowed(int requestId) {
        if(userRequest.size()<maxSize){
            userRequest.add(requestId);
            return true;
        }
        return false;
    }

    /*
     * This algorithm executes at fixed rate, so handles little bit low traffic than token bucket algo.
     */
    public void refillAtFixedRate(){
        executorService.scheduleAtFixedRate(() -> {
            int len = operations;
            while(!userRequest.isEmpty() && len>0){
                System.out.println("Executed -> "+ userRequest.peek());
                userRequest.poll();
            }
        },0,fixedInterval, TimeUnit.SECONDS);
    }
}
