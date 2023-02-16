package com.example.demo.mapper;

import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.User;
@Repository
@Mapper
public interface RoleMapper {
    List<User> findAll();
    Set<String> findRole(String name);
    void saveRole(User user);
    void deleteRole(User user);
}
