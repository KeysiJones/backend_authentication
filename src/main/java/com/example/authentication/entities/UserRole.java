package com.example.authentication.entities;

import com.example.authentication.enums.RoleType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class UserRole {
    @Id
    private String id;

    private RoleType name;

    public UserRole() {

    }

    public UserRole(RoleType name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }
}