package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.*;
import com.example.demo.domain.*;

import java.util.*;

@Transactional
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

    public List<Role> findAll(){
        return roleMapper.findAll();
    }

    public String findRole(String name) {
        return roleMapper.findRole(name);
    }    

    public String findPolicy(String role) {
        return policyMapper.findPolicy(role);
    }

    public void save(User user) {
        userMapper.save(user);
    }

    public void saveRole(Role role) throws RuntimeException {
        String policy = policyMapper.findPolicy(role.getName());
        if(policy==null){
            throw new RuntimeException(String.format("the role %s doesn't exist", role.getName()));
        }else{
            roleMapper.saveRole(role);
        }
    }

    public void delete(User user){
        userMapper.delete(user);
    }

    public void deleteRole(Role role){
        roleMapper.deleteRole(role);    
    }
 }
