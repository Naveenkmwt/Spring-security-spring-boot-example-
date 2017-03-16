package com.scube.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scube.auth.model.DbUser;

public interface UserRepository extends JpaRepository<DbUser, Long> {
    DbUser findByUsername(String username);
    DbUser findByClientId(String clientId);
    
}
