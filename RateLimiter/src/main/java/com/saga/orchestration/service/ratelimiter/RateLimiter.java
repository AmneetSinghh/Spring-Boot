package com.saga.orchestration.service.ratelimiter;

import com.saga.orchestration.util.Identifier;

public interface RateLimiter {
    void registerService(String operation, int operations, int seconds, Identifier identifier);
    boolean isRequestValid(String operation, Identifier identifier, String idValue);
}
