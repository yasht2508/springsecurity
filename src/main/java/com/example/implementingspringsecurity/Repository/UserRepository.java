package com.example.implementingspringsecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.implementingspringsecurity.Entity.User;

public interface UserRepository extends JpaRepository<User,String>{

    public User findByUsername(String username);

}
