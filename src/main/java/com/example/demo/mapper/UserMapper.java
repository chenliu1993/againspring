package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.User;

import java.util.*;
@Mapper
public interface UserMapper {
    User findOne(String name);
    Set<String> findRole(String name);
    Set<String> findPolicy(String role);

    void save(User user);
    void saveRole(User user);
}
