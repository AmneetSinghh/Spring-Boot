package com.saga.orchestration.service.ratelimiter;

import com.saga.orchestration.model.RateLimiterConfig;
import com.saga.orchestration.service.ratelimiter.strategy.TockenBucketLimiter;
import com.saga.orchestration.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterImpl implements RateLimiter{

    private final Map<String, RateLimiterConfig> services;// for registering services/Apis.     [redis]

    private final Map<String, TockenBucketLimiter> serviceBuckets; // for running rate limiter algo.    [redis]

    public RateLimiterImpl() {
        this.services = new HashMap<>();
        this.serviceBuckets = new HashMap<>();
    }


    /*
     * rateLimiter Config : Fields we can add -> rateLimiterStrategyType, other info etc.
     */
    @Override
    public void registerService(String operation, int operations, int seconds, Identifier identifier) {
        String key = getServiceKey(operation,identifier);
        services.put(key,new RateLimiterConfig(operations,operation,identifier,seconds));
    }

    @Override
    public boolean isRequestValid(String operation, Identifier identifier, String idValue) {
        String serviceKey = getServiceKey(operation,identifier);
        /*
         * Api/operation registered in servicesMap/redis.
         */
        if(!services.containsKey(serviceKey)){
            throw new RuntimeException("api not registered");
        }
        RateLimiterConfig config = services.get(serviceKey);
        String opKey = getOperationKey(operation,identifier,idValue);
        if(!serviceBuckets.containsKey(opKey)){
            serviceBuckets.put(opKey,new TockenBucketLimiter(config.getOperations(),config.getSeconds()));
        }

        TockenBucketLimiter tockenBucketLimiter = serviceBuckets.get(opKey);
        return tockenBucketLimiter.isRequestAllowed();

    }


    /*
     * example apiname_userId
     * some api I want userId level, some api's to have session level rate limiter.
     */
    private String getServiceKey(String operation, Identifier identifier){
        return operation.concat("_" + identifier.toString());
    }

    // example apiname_userId_value
    private String getOperationKey(String operation, Identifier identifier,String value){
        return operation.concat("_" + identifier.toString()).concat("_" + value);
    }

}

