package com.example.BETest.controller;

import com.example.BETest.object.Student;
import com.example.BETest.response.BaseResponse;
import com.example.BETest.response.ResponseExists;
import com.example.BETest.response.ResponseStudent;
import com.example.BETest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
//@ComponentScan(basePackages = "service.")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public BaseResponse fetchAllStudents(){
        return new ResponseStudent(HttpStatus.OK.value(), studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseStudent getStudent(@PathVariable(name = "id") String id){
        Student student = studentService.findStudentById(id);
        ResponseStudent response = new ResponseStudent(HttpStatus.OK.value(),Arrays.asList(student));
        return response;
    }

    @GetMapping("/find")
    public ResponseStudent findStudent(@RequestParam(name = "search") String search,@RequestHeader(name = "Authorization") String Authorization){
        List<Student> student = studentService.findStudent(search);
        ResponseStudent response = new ResponseStudent(HttpStatus.OK.value(),student);
        return response;
    }

    @PostMapping("")
    public BaseResponse addNewStudent(@RequestBody Student student){
        if(studentService.addNewStudent(student).compareTo("successful") == 0) {
            return new ResponseStudent(HttpStatus.OK.value(), Arrays.asList(student));
        }else {
            return new ResponseExists(HttpStatus.ACCEPTED.value(),studentService.addNewStudent(student));
        }
    }

    @PutMapping("")
    public BaseResponse updateStudent(@RequestBody Student student){
        if(studentService.updateStudent(student).compareTo("successful") == 0) {
            return new ResponseStudent(HttpStatus.OK.value(), Arrays.asList(student));
        }else {
            return new ResponseExists(HttpStatus.ACCEPTED.value(),studentService.updateStudent(student));
        }
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse deleteStudent(@PathVariable(name = "id") String id){
        studentService.deleteStudent(id);
        return new ResponseExists(HttpStatus.OK.value(),"delete successful");
    }

//    @GetMapping("/findd")
//    public Optional<Student> findStudentsByEmail(@RequestParam(name = "email") String email){
//        return studentService.findStudentByEmail(email);
//    }
}
