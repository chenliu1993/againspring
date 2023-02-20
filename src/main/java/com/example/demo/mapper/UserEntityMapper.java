package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.UserEntity;;
@Mapper
public interface UserEntityMapper {
    UserEntity findUserEntity(String name);
}
