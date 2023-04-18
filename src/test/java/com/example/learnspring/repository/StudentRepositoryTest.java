package com.example.learnspring.repository;

import com.example.learnspring.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ListResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("trancongdat@gmail.com")
                .firstName("Dat")
                .lastName("Tran")
//                .guardianEmail("trandinh@gmail.com")
//                .guardianMobile("01920192")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents() {
        List<Student> students = studentRepository.findAll();

        System.out.println("Student List: " + students);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("Dat");

        System.out.println("Students find by first name: " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("at");

        System.out.println("Student : " + students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("trancongdat@gmail.com");

        System.out.println("Student: " + student);
    }
}