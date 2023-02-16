package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.domain.User;;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findOne(String name){
        return userMapper.findOne(name);
    }

    public String findRole(String name) {
        return userMapper.findRole(name);
    }

    public String findPolicy(String role) {
        return userMapper.findPolicy(role);
    }
 }
