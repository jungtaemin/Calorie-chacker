package com.tripproject.upload.service;

import com.tripproject.upload.domain.Upload;
import com.tripproject.upload.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class UploadService {

    private final FileFactory fileFactory;
    private final UploadRepository uploadRepository;

    public Upload saveUploadImg(MultipartFile multipartFile) throws IOException {
        Upload upload = fileFactory.saveImg(multipartFile);
        Upload resultUpload = dbSave(upload);
        return resultUpload;
    }


    public Upload dbSave(Upload upload){
        return uploadRepository.save(upload);

    }
}
