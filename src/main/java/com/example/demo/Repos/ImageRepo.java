package com.example.demo.Repos;

import com.example.demo.Entities.ImageEntity;
import com.example.demo.Entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Repository
@Transactional
public interface ImageRepo extends JpaRepository<ImageEntity,Long> {
    Optional <ImageEntity> findById(Long id);
}
