package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import lombok.Data;
import java.util.*;

@Data
@Repository
public class User {
    @Id
    private Long id;
    private String name;
    private String password;
    // currently for convenient, one role per user
    private Set<String> role;
    private Set<String> policy;
}
