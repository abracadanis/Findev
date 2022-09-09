package com.example.demo.Repos;


import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ProjectRepo extends JpaRepository<ProjectEntity,Long> {
    Optional<ProjectEntity> findProjectById(Long id);
}
