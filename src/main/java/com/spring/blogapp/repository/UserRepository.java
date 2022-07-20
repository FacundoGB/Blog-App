package com.spring.blogapp.repository;

import com.spring.blogapp.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    public Optional<MyUser> findByEmail(String email);

    public Optional<MyUser> findByUsernameOrEmail(String username, String email);

    public Optional<MyUser> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
