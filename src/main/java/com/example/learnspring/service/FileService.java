package com.example.learnspring.service;

import com.example.learnspring.entity.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface FileService {
    File save(MultipartFile file) throws Exception;

    File getFile(String fileId) throws Exception;
}
