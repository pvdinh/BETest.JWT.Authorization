package com.example.BETest.service;

import com.example.BETest.object.Teacher;
import com.example.BETest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> findAllTeacher(){
        return teacherRepository.findAll();
    }

    public Teacher findTeacherById(String id){
        return teacherRepository.findTeacherById(id);
    }
}
