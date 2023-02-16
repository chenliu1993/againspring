package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.domain.User;

import java.util.*;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findOne(String name){
        return userMapper.findOne(name);
    }

    public List<User> findAll(){
        return userMapper.findAll();
    }

    public Set<String> findRole(String name) {
        return userMapper.findRole(name);
    }

    public Set<String> findPolicy(String role) {
        return userMapper.findPolicy(role);
    }

    public void save(User user) {

        Set<String> targetPolicy = new HashSet<String>();
        Set<String> roles = user.getRole();
        for (Iterator<String> it = roles.iterator(); it.hasNext(); ) {
            String role = it.next();
            Set<String> singlePolicy = userMapper.findPolicy(role);
            targetPolicy.addAll(singlePolicy);
        }
       
        user.setPolicy(targetPolicy);
        userMapper.save(user);
        userMapper.saveRole(user);
    }

    public void delete(User user){
        userMapper.delete(user);
        userMapper.deleteRole(user);
    }
 }
