package com.example.demo.domain;

import lombok.Data;
import java.util.*;;


@Data
public class UserEntity {
    private String name;
    private String password;
    // currently for convenient, one role per user
    private Set<String> role;
}
