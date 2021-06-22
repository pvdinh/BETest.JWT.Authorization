package com.example.BETest.object;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;

@Document
public class MajorsClass {
    private String id;
    private String name;
    private int totalStudent;
    private HashSet<String> listStudents;

    public MajorsClass() {
        super();
    }

    public MajorsClass(String id, String name, HashSet<String> listStudents) {
        this.id = id;
        this.name = name;
        this.listStudents = listStudents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalStudent() {
        return listStudents.size();
    }

    public void setTotalStudent() {
        this.totalStudent = listStudents.size();
    }

    public HashSet<String> getListStudents() {
        return listStudents;
    }

    public void setListStudents(HashSet<String> listStudents) {
        this.listStudents = listStudents;
    }
}
