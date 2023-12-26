package com.example.cacheservice.service;

import com.example.cacheservice.dao.RedisDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedisDao redisDao;
    public void saveData(String key, Map<String, Object> cacheDataMap) {
        log.info("Entered RedisService to method saveData with key={} & data={}", key, cacheDataMap);
        redisDao.saveData(key, cacheDataMap);
    }

    public Map<String, Object> getData(String key) {
        log.info("Entered RedisService to method getData with key={}", key);
        return redisDao.getData(key);
    }

    public Boolean deleteData(String key) {
        log.info("Entered RedisService to method deleteData with key={}", key);
        return redisDao.deleteData(key);
    }

    public String get(String key) {
//        log.info("Entered RedisService to method getData with key={}", key);
        return redisDao.get(key);
    }

    public void set(String key, String value) {
//        log.info("Entered RedisService to method saveData with key={} & value={}", key, value);
        redisDao.set(key,value);
    }

    public Boolean setNx(String key, String value) {
//        log.info("/*Entered*/ RedisService to method setIfNotExists with key={} & value={}", key, value);
        return redisDao.setNX(key,value);
    }

    public Boolean setNx(String key, String value, Duration expiry) {
//        log.info("/*Entered*/ RedisService to method setIfNotExists with key={} & value={}", key, value);
        return redisDao.setNX(key,value,expiry);
    }

}
