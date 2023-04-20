package com.example.learnspring.repository;

import com.example.learnspring.entity.Course;
import com.example.learnspring.entity.CourseMaterial;
import com.example.learnspring.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course course_1 = Course.builder()
                .title("Course 1")
                .credit(4)
                .build();

        Course course_2 = Course.builder()
                .title("Course 2")
                .credit(5)
                .build();


        Teacher teacher = Teacher.builder()
                .firstName("Tran")
                .lastName("Dat")
//                .courses(List.of(course_1, course_2))
                .build();

        teacherRepository.save(teacher);
    }

}