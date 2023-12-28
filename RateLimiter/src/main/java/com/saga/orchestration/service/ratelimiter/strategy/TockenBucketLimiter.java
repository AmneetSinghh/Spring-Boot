package com.saga.orchestration.service.ratelimiter.strategy;

import java.time.Instant;

public class TockenBucketLimiter implements RateLimiterStrategy{
    private int tokens;
    private int interval;
    private long lastRefillTime; // in Epoch seconds.
    private int refillTokens;
    private int bucketSize;

    public TockenBucketLimiter(int operations, int seconds){
        this.interval = seconds;
        this.tokens = operations; // current tokens
        this.bucketSize = operations; // bucket size
        this.refillTokens = operations; // refill tokens
        this.lastRefillTime = Instant.now().getEpochSecond();
    }

    @Override
    public synchronized boolean isRequestAllowed() {
        refill();
        if(tokens > 0){
            -- tokens;
            return true;
        }
        return false;
    }

    private void refill(){
        long currentEpoc = Instant.now().getEpochSecond();
        long timeElapsed = currentEpoc - lastRefillTime;
        if(timeElapsed >= interval){
            int tokensToAdd = (int) ((timeElapsed / interval) * refillTokens);
            tokens = Math.min(bucketSize,tokens + tokensToAdd);
            lastRefillTime = lastRefillTime + ((timeElapsed / interval) * interval);
        }

    }
}
