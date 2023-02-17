package com.example.demo.domain;

import org.springframework.stereotype.Repository;
import org.springframework.data.annotation.Id;

import lombok.Data;
import java.util.*;

@Data
@Repository
public class Role {
    @Id
    private Long id;
    private String name;
    private Set<String> role;
}
