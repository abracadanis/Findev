package com.example.demo.repositories;


import com.example.demo.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
    Optional<ProjectEntity> findProjectById(Long id);

    @Query("SELECT p FROM ProjectEntity p WHERE p.isDraft = :isDraft")
    List<ProjectEntity> findProjectsByDraft(Boolean isDraft);

    @Query("SELECT p.users FROM ProjectEntity p")
    List<Long> findUserIdsByProjectId(Long id);

    @Modifying
    @Query("UPDATE ProjectEntity p SET p.deleted = true WHERE p.id = :id")
    void deleteProjectById(Long id);
}
