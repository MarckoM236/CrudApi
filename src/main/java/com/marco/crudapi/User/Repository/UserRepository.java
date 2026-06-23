package com.marco.crudapi.User.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.crudapi.User.Entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmailContaining(String email);
    public boolean existsByEmail(String email);
}
