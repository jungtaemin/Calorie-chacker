package com.tripproject.upload.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Upload {
    @Id @GeneratedValue
    @Column(name = "upload_id")
    private Long id;
    private String originalFilename;
    private String storeFileName;


    public Upload(String originalFilename, String storeFileName){
    this.originalFilename = originalFilename;
    this.storeFileName = storeFileName;
    }
}
