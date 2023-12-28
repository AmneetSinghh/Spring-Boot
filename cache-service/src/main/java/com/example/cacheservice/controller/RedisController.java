package com.example.cacheservice.controller;

import com.example.cacheservice.service.CacheBreakdown;
import com.example.cacheservice.service.RedisService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/redis")
@RequiredArgsConstructor

public class RedisController {


    private final RedisService redisService;

    @Autowired
    private CacheBreakdown cacheBreakdown;


    @PostMapping
    public ResponseEntity<HttpStatus> saveData(@NonNull @RequestParam String key,
                                               @RequestBody Map<String, Object> cacheDataMap) {
        log.info("Entered RedisController to method saveData with key={} & data={}", key, cacheDataMap);
        redisService.saveData(key, cacheDataMap);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/map")
    public ResponseEntity<Map<String, Object>> getData(@NonNull @RequestParam String key) {
        log.info("Entered RedisController to method getData with key={}", key);
        Map<String, Object> cacheData = redisService.getData(key);
        if (Objects.isNull(cacheData))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(cacheData);
    }

    @GetMapping("/getKey")
    public ResponseEntity<String> get(@NonNull @RequestParam String key) {
        log.info("Entered RedisController to method get with key={}", key);
        String value = redisService.get(key);
        if (value != null) {
            return ResponseEntity.ok().body(value);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/setKey")
    public ResponseEntity<String> set(@NonNull @RequestParam String key, @NonNull @RequestParam String value) {
        log.info("Entered RedisController to method get with key={}", key);
        redisService.set(key,value);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/cacheBreakdown")
    public ResponseEntity<String> cacheBreakdown() {
        log.info("Pass blogId and cache breakdown/ debouncing");
        cacheBreakdown.setUp();
        return ResponseEntity.status(HttpStatus.OK).build();
    }



//    @DeleteMapping
//    public ResponseEntity<Void> deleteData(@NonNull @RequestParam String key) {
//        log.info("Entered RedisController to method deleteData with key={}", key);
//        boolean isDeleted = redisService.deleteData(key);
//        if (!isDeleted)
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
//


}
