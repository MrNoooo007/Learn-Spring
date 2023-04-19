package com.example.learnspring.controller;

import com.example.learnspring.repository.CourseMaterialRepository;
import com.example.learnspring.repository.CourseRepository;
import com.example.learnspring.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @GetMapping
    public ResponseEntity<ResponseObject> index() {
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "OK", courseRepository.findAll())
        );
    }

    @GetMapping("/material")
    public ResponseEntity<ResponseObject> get() {
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "OK", courseMaterialRepository.findAll())
        );
    }

}
