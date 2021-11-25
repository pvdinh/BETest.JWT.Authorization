package com.example.BETest.repository;

import com.example.BETest.object.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher,String> {
    Teacher findTeacherById(String id);
}
