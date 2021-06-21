package com.example.BETest.service;

import com.example.BETest.repository.StudentRepository;
import com.example.BETest.object.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    private final String SUCCESS ="successful";
    private final String EXISTS ="already exists";
    private final String NOT_EXISTS ="already not exists";
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public List<Student> findStudentByEmail(String email){
        return studentRepository.findStudentByEmail(email);
    }
    public List<Student> findStudent(String s){
        List<Student> result = new ArrayList<>();
        studentRepository.findAll().forEach(student -> {
            if(student.getId().contains(s) || student.getEmail().contains(s)){
                result.add(student);
            }
        });
        return result;
    }
    public Student findStudentById(String id){
        return studentRepository.findStudentById(id);
    }
    public String addNewStudent(Student student) {
        try{
            studentRepository.insert(student);
            return SUCCESS;
        }catch (org.springframework.dao.DuplicateKeyException e){
            return EXISTS;
        }
    }
    public void deleteStudent(String id){
        studentRepository.deleteById(id);
    }
    public String updateStudent(Student student){
        if(studentRepository.findStudentById(student.getId()) != null){
            try{
                studentRepository.save(student);
                return SUCCESS;
            }catch (org.springframework.dao.DuplicateKeyException e){
                return EXISTS;
            }
        }else return NOT_EXISTS;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Student student = studentRepository.findStudentById(s);
        if(student == null){
            throw new NullPointerException("NOT FOUND : " + s);
        }else {
            return org.springframework.security.core.userdetails.User
                    .withUsername(student.getId())
                    .password("{noop}"+student.getPassword())
                    .authorities(Collections.emptyList())
                    .build();
        }
    }
}
