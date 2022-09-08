package com.example.demo.Repos;


import com.example.demo.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProjectRepo extends JpaRepository<Project,Long> {
}
