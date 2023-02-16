package com.example.demo.mapper;

import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.*;
@Repository
@Mapper
public interface RoleMapper {
    List<Role> findAll();
    Set<String> findRole(String name);
    void saveRole(Role role);
    void deleteRole(Role role);
}
