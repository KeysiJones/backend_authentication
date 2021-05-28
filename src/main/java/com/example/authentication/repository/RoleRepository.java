package com.example.authentication.repository;

import com.example.authentication.entities.UserRole;
import com.example.authentication.enums.RoleType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<UserRole, String> {
    Optional<UserRole> findByName(RoleType name);
}
