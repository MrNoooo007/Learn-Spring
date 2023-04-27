package com.example.learnspring.controller;

import com.example.learnspring.entity.File;
import com.example.learnspring.response.ResponseData;
import com.example.learnspring.service.FileServiceImplement;
import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class FileController {

    private FileServiceImplement fileServiceImplement;

    FileController(FileServiceImplement fileService) {
        this.fileServiceImplement = fileService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("image") MultipartFile image) throws Exception {
        File file = null;
        String downloadURL = "";
        file = fileServiceImplement.save(image);

        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(file.getId()))
                .toUriString();

        return new ResponseData(
                file.getFileName(), downloadURL, image.getContentType(), image.getSize()
        );
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        File file = null;
        file = this.fileServiceImplement.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\""
                        + file.getFileName() + "\"")
                .body((Resource) new ByteArrayResource(file.getData()));
    }
}
