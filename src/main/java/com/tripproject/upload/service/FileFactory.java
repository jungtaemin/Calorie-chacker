package com.tripproject.upload.service;

import com.tripproject.upload.domain.Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileFactory {


    @Value("${file.dir}")
    private String fileDir;


    public Upload saveImg(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename =  multipartFile.getOriginalFilename();

        String storeName = getStoreName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeName)));
       return new Upload(originalFilename,storeName);
    }

    public String getFullPath(String storeName){
        return fileDir+storeName;
    }


    private String getStoreName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        int i = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(i + 1);
        String storeName = uuid+"."+ext;
        return storeName;
    }
}
