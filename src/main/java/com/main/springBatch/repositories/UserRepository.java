package com.main.springBatch.repositories;

import com.main.springBatch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
