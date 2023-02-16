package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class Role {
    private String name;
    private String role;
}
