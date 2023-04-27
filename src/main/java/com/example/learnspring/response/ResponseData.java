package com.example.learnspring.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {

    private String filename;
    private String downloadUrl;
    private String fileType;
    private long fileSize;
}
