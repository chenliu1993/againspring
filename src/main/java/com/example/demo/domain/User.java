package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import lombok.Data;
// import java.util.*;

@Data
@Repository
public class User {
    @Id
    private Long id;

    private String name;
    private String password;
    private String role;
    // 1, root, root
}
