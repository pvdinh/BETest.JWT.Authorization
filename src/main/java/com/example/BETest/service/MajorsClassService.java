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
    private final String SUCCESS ="successful";
    private final String EXISTS ="already exists";
    private final String NOT_EXISTS ="already not exists";

    public List<MajorsClass> getAllMajorsClass(){
        return majorsClassRepository.findAll();
    }

    public String addNew(MajorsClass majorsClass){
        try{
            majorsClassRepository.insert(majorsClass);
            return SUCCESS;
        }catch (DuplicateKeyException e){
            return EXISTS;
        }
    }
}
