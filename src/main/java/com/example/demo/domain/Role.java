package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import lombok.Data;
import java.util.*;

@Data
@Repository
public class Role {
    private String name;
    private Set<String> role;
}
