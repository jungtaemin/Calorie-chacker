package com.tripproject.upload.repository;

import com.tripproject.upload.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload,Long> {
}
