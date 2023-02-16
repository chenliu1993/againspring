package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.User;

@Mapper
public interface UserMapper {
    User findOne(String name);
    String findRole(String name);
    String findPolicy(String role);
}
