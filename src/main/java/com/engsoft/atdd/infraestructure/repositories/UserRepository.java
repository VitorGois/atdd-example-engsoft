package com.engsoft.atdd.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.engsoft.atdd.domain.models.User;
import com.engsoft.atdd.infraestructure.exceptions.NotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email.value = :email")
    User findByEmail(@Param("email") String email) throws NotFoundException;
}
