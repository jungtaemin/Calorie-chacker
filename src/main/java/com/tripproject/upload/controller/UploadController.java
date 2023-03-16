package com.tripproject.upload.controller;

import com.tripproject.upload.service.FileFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final FileFactory fileFactory;



    @GetMapping("/downloadImg/{storeName}")
    public Resource downloadImg(@PathVariable String storeName) throws MalformedURLException {
        return new UrlResource("file:"+fileFactory.getFullPath(storeName));
    }
}
