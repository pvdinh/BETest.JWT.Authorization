package com.example.BETest.controller;

import com.example.BETest.object.MajorsClass;
import com.example.BETest.response.BaseResponse;
import com.example.BETest.response.ResponseMessage;
import com.example.BETest.response.Response;
import com.example.BETest.service.MajorsClassService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/class")
@Api(value = "/class", tags = "Class", description = "Manage Class")
public class MajorsClassController {
    @Autowired
    private MajorsClassService majorsClassService;

    @GetMapping
    @ApiOperation(value = "Lấy ra danh sách các lớp chuyên ngành")
    public Response getAllMajorsClass() {
        return new Response(HttpStatus.OK.value(), majorsClassService.getAllMajorsClass());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Thông tin lớp chuyên ngành")
    public Response getMajorsClass(@PathVariable(name = "id") String id) {
        return new Response(HttpStatus.OK.value(), Arrays.asList(majorsClassService.getMajorsClass(id)));
    }

    @PostMapping
    @ApiOperation(value = "Thêm mới lớp chuyên ngành")
    public BaseResponse addNew(@RequestBody MajorsClass majorsClass) {
        if (majorsClassService.addNew(majorsClass).compareTo("successful") == 0) {
            return new Response(HttpStatus.OK.value(), Arrays.asList(majorsClass));
        } else {
            return new ResponseMessage(HttpStatus.ACCEPTED.value(), majorsClassService.addNew(majorsClass));
        }
    }

    @PutMapping("/addStudent")
    @ApiOperation(value = "Thêm một học sinh vào lớp chuyên ngành")
    public BaseResponse addNewStudentToClass(@RequestBody MajorsClass majorsClass, @RequestParam(name = "idStudent") String studentId) {
        if (majorsClassService.addStudentToClass(studentId, majorsClass).compareTo("successful") == 0) {
            return new Response(HttpStatus.OK.value(), Arrays.asList(majorsClass));
        } else {
            return new ResponseMessage(HttpStatus.ACCEPTED.value(), majorsClassService.addStudentToClass(studentId, majorsClass));
        }
    }

    @PutMapping("/deleteStudent")
    @ApiOperation(value = "Xoá một học sinh khỏi lớp chuyên ngành")
    public BaseResponse deleteStudentFromClass(@RequestBody MajorsClass majorsClass, @RequestParam(name = "idStudent") String studentId) {
        majorsClassService.deleteStudentFromClass(studentId, majorsClass);
        return new ResponseMessage(HttpStatus.OK.value(), "delete successful");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Xoá một lớp chuyên ngành")
    public BaseResponse delete(@RequestParam(name = "id") String id) {
        majorsClassService.delete(id);
        return new ResponseMessage(HttpStatus.OK.value(), "delete successful");
    }

}
