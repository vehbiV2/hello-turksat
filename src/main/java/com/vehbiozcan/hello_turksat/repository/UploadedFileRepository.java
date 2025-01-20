package com.vehbiozcan.hello_turksat.repository;

import com.vehbiozcan.hello_turksat.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
}
