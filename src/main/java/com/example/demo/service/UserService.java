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
 }
