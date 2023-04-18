package com.example.learnspring.repository;

import com.example.learnspring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);

    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);
}
