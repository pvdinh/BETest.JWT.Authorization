package com.example.BETest.service;

import com.example.BETest.object.MajorsClass;
import com.example.BETest.repository.StudentRepository;
import com.example.BETest.object.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MajorsClassService majorsClassService;

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
        try{
            studentRepository.deleteById(id);
            MajorsClass majorsClass = majorsClassService.findMajorClassByIdStudent(id);
            if(majorsClass.getId() != null)
            majorsClassService.deleteStudentFromClass(id,majorsClass);
        }catch (Exception e){
            throw e;
        }
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
}
