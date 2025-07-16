package com.example.demo.repositories;

import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByUsernameAndDeletedIsFalse(String username);

    @Modifying
    @Query("UPDATE UserEntity u SET u.deleted = true WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);
}
