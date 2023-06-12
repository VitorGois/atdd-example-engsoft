package com.engsoft.coursesapi.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.engsoft.coursesapi.domain.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email.value = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE (:nameFilter IS NULL OR u.name = :nameFilter) AND (:emailFilter IS NULL OR u.email.value = :emailFilter)")
    List<User> findByFilter(@Param("nameFilter") String nameFilter, @Param("emailFilter") String emailFilter);
}
