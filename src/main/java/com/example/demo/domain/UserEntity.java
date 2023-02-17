package com.example.demo.domain;

import lombok.Data;
import org.springframework.stereotype.Repository;


@Data
@Repository
public class UserEntity {
    private String name;
    private String password;
    // currently for convenient, one role per user
    // Assume this is seperated by ,
    private String role;
}
