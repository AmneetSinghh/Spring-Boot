package com.example.cacheservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheServiceApplication.class, args);
	}
}

/*
Cache-Breakdown / Cache Debouncing Problem: 100k request same time want to access hot/trending blog:

3 api servers:
1 Db
1 central cache

- questions:
1. in-memory LRU cache:
    - will work well when expiry is not set in redis for hot data, so reducing some calls to redis/db
    - will not work when expiry set in redis.



if loadBalancer sticky sessions based on blogId:    ( impossible )
    - Request for same blog will go to same api server always.
    - no need for distributed locking. ( redis setNx)
    - just use concurrentHashmap / Simple Locking - lock( blogId)   -      critical section         - unlock(BlogId)

if loadBalancer stateless:    ( impossible )
    - Request for same blog will go to any api server.
    - Need for distributed locking. ( redis setNx)
    - api level locking doesn't work.

we never do application level locking.
*/
