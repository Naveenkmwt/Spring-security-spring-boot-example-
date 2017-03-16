package com.scube.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scube.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
