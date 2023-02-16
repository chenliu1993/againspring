package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.User;


@Mapper
public interface UserMapper {
    User findOne(String name);

    void save(User user);

    void delete(User user);

}
