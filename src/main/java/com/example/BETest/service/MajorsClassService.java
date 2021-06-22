package com.example.BETest.service;

import com.example.BETest.object.MajorsClass;
import com.example.BETest.repository.MajorsClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorsClassService {
    @Autowired
    private MajorsClassRepository majorsClassRepository;
    @Autowired
    private StudentService studentService;
    private final String SUCCESS = "successful";
    private final String EXISTS = "already exists";
    private final String STUDENT_EXISTS = "students already exists";
    private final String NOT_EXISTS = "students already not exists";

    public List<MajorsClass> getAllMajorsClass() {
        return majorsClassRepository.findAll();
    }

    public MajorsClass getMajorsClass(String id) {
        return majorsClassRepository.findMajorsClassById(id);
    }

    public String addNew(MajorsClass majorsClass) {
        try {
            majorsClassRepository.insert(majorsClass);
            return SUCCESS;
        } catch (DuplicateKeyException e) {
            return EXISTS;
        }
    }

    public void delete(String id) {
        majorsClassRepository.delete(majorsClassRepository.findMajorsClassById(id));
    }

    public String addStudentToClass(String studentId, MajorsClass majorsClass) {
        if (studentService.findStudentById(studentId) != null) {
            if (!majorsClass.getListStudents().contains(studentId)) {
                majorsClass.getListStudents().add(studentId);
                majorsClass.setListStudents(majorsClass.getListStudents());
                majorsClassRepository.save(majorsClass);
                return SUCCESS;
            } else return STUDENT_EXISTS;
        } else return NOT_EXISTS;
    }

    public void deleteStudentFromClass(String studentId, MajorsClass majorsClass) {
        majorsClass.getListStudents().remove(studentId);
        majorsClass.setListStudents(majorsClass.getListStudents());
        majorsClassRepository.save(majorsClass);
    }
}
