package com.example.demo.Repos;


import com.example.demo.Entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProjectRepo extends JpaRepository<ProjectEntity,Long> {
    Optional<ProjectEntity> findProjectById(Long id);

    @Query("SELECT p FROM ProjectEntity p WHERE p.isDraft = ?1")
    List<ProjectEntity> findProjectsByDraft(Boolean isDraft);
}
