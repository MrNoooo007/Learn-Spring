package com.example.learnspring.repository;

import com.example.learnspring.entity.Course;
import com.example.learnspring.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .title("title ne")
                .credit(1000)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("youtube.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourse() {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();

        System.out.println("Course: " + courseMaterials);
    }
}