package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
@Mapper
@Repository
public interface PolicyMapper {
    Set<String> findPolicy(String role);
}
