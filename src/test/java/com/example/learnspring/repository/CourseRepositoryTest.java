package com.example.learnspring.repository;

import com.example.learnspring.entity.Course;
import com.example.learnspring.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourse() {
        Course course = Course.builder()
                .title("title 1")
                .credit(1000)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void printAllCourse() {
        List<Course> courses = courseRepository.findAll();

        System.out.println("Course : " + courses);
    }
}