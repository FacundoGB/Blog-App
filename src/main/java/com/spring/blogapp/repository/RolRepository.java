package com.spring.blogapp.repository;

import com.spring.blogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByName(String name);

}
