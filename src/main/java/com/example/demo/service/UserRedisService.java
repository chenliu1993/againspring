package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class UserRedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String name, String role, Long expiredTime) {
        if (expiredTime > 0) {
            // Only seconds
            redisTemplate.opsForValue().set(name, role, expiredTime, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(name, role);
        }
    }

    public Object get(String name) {
        return redisTemplate.opsForValue().get(name);
    }

    public void delete(String name) {
        redisTemplate.delete(name);
    }
}
