package com.example.BETest.controller;

import com.example.BETest.object.Student;
import com.example.BETest.response.BaseResponse;
import com.example.BETest.response.ResponseMessage;
import com.example.BETest.response.Response;
import com.example.BETest.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
@PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
@Api(value = "/Student", tags = "Student", description = "Manage Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    @ApiOperation(value = "Lấy ra danh sách sinh viên")
    public BaseResponse fetchAllStudents(){
        return new Response(HttpStatus.OK.value(), studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lấy ra thông tin sinh viên theo msv")
    public Response getStudent(@PathVariable(name = "id") String id){
        Student student = studentService.findStudentById(id);
        Response response = new Response(HttpStatus.OK.value(),Arrays.asList(student));
        return response;
    }


    //  @RequestHeader(name = "Authorization") String Authorization
    @GetMapping("/find")
    @ApiOperation(value = "Tìm kiếm sinh viên theo msv hoặc email")
    public Response findStudent(@RequestParam(name = "search") String search){
        List<Student> student = studentService.findStudent(search);
        Response response = new Response(HttpStatus.OK.value(),student);
        return response;
    }

    @PostMapping("")
    @ApiOperation(value = "Thêm mới sinh viên")
    public BaseResponse addNewStudent(@RequestBody Student student){
        if(studentService.addNewStudent(student).compareTo("successful") == 0) {
            return new Response(HttpStatus.OK.value(), Arrays.asList(student));
        }else {
            return new ResponseMessage(HttpStatus.ACCEPTED.value(),studentService.addNewStudent(student));
        }
    }

    @PutMapping("")
    @ApiOperation(value = "Chỉnh sửa thông tin sinh viên")
    public BaseResponse updateStudent(@RequestBody Student student){
        if(studentService.updateStudent(student).compareTo("successful") == 0) {
            return new Response(HttpStatus.OK.value(), Arrays.asList(student));
        }else {
            return new ResponseMessage(HttpStatus.ACCEPTED.value(),studentService.updateStudent(student));
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Xoá sinh viên")
    public BaseResponse deleteStudent(@PathVariable(name = "id") String id){
        studentService.deleteStudent(id);
        return new ResponseMessage(HttpStatus.OK.value(),"delete successful");
    }

//    @GetMapping("/findd")
//    public Optional<Student> findStudentsByEmail(@RequestParam(name = "email") String email){
//        return studentService.findStudentByEmail(email);
//    }
}
