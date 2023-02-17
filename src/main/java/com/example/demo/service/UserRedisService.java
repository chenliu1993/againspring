package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
@Service
public class UserRedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String name, String role){
        redisTemplate.opsForValue().set(name, role, 100, TimeUnit.SECONDS);
    }

    public Object get(String name) {
        return redisTemplate.opsForValue().get(name);
    }
}
