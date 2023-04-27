package com.example.learnspring.service;

import com.example.learnspring.entity.File;
import com.example.learnspring.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class FileServiceImplement implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File save(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if(fileName.contains("...")) {
                throw new Exception("FileName contains invalid path sequence " + fileName);
            }
            File fileUploaded = new File(fileName,file.getName(),file.getBytes());
            return fileRepository.save(fileUploaded);
        }
        catch (Exception exception) {
            throw new Exception("Could not save your file");
        }
    }

    @Override
    public File getFile(String fileId) throws Exception {
        return fileRepository.findById(fileId).orElseThrow(() -> new Exception("Not found: " + fileId));
    }
}
