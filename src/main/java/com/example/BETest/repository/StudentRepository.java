package com.example.BETest.repository;

import com.example.BETest.object.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findStudentByEmail(String email);
    Student findStudentById(String id);
}
