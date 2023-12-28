package com.saga.orchestration.service.ratelimiter.strategy;

public interface RateLimiterStrategy {

    public boolean isRequestAllowed();

}
