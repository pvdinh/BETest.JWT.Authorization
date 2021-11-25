package com.example.BETest.controller;

import com.example.BETest.response.Response;
import com.example.BETest.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/teachers")
@Api(value = "/Teacher",tags = "teacher",description = "Manage Teacher")
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public Response findAllTeacher(){
        return new Response(HttpStatus.OK.value(),teacherService.findAllTeacher());
    }

    @GetMapping("/{id}")
    public Response findTeacherById(@PathVariable(name = "id") String id){
        return new Response(HttpStatus.OK.value(), Arrays.asList(teacherService.findTeacherById(id)));
    }
}
