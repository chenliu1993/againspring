package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.*;
import com.example.demo.domain.User;

import java.util.*;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private RoleMapper roleMapper;

    public User findOne(String name){
        return userMapper.findOne(name);
    }

    public List<User> findAll(){
        return roleMapper.findAll();
    }

    public Set<String> findRole(String name) {
        return roleMapper.findRole(name);
    }

    public Set<String> findPolicy(String role) {
        return policyMapper.findPolicy(role);
    }

    public void save(User user) {

        Set<String> targetPolicy = new HashSet<String>();
        Set<String> roles = user.getRole();
        for (Iterator<String> it = roles.iterator(); it.hasNext(); ) {
            String role = it.next();
            Set<String> singlePolicy = policyMapper.findPolicy(role);
            targetPolicy.addAll(singlePolicy);
        }
       
        user.setPolicy(targetPolicy);
        userMapper.save(user);
        roleMapper.saveRole(user);
    }

    public void delete(User user){
        userMapper.delete(user);
        roleMapper.deleteRole(user);
    }
 }
