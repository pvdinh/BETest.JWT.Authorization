package com.example.BETest.controller;

import com.example.BETest.object.MajorsClass;
import com.example.BETest.response.BaseResponse;
import com.example.BETest.response.ResponseExists;
import com.example.BETest.response.ResponseStudent;
import com.example.BETest.service.MajorsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("api/v1/class")
public class MajorsClassController {
    @Autowired
    private MajorsClassService majorsClassService;

    @GetMapping
    public ResponseStudent getAllMajorsClass() {
        return new ResponseStudent(HttpStatus.OK.value(),majorsClassService.getAllMajorsClass());
    }

    @PostMapping
    public BaseResponse addNew(@RequestBody MajorsClass majorsClass) {
        if(majorsClassService.addNew(majorsClass).compareTo("successful") == 0){
            return new ResponseStudent(HttpStatus.OK.value(), Arrays.asList(majorsClass));
        }else {
            return new ResponseExists(HttpStatus.ACCEPTED.value(),majorsClassService.addNew(majorsClass));
        }
    }

}
