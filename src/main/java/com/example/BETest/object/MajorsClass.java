package com.example.BETest.object;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MajorsClass {
    private String id;
    private String name;
    private int totalStudent;
    private List<String> listStudents;

    public MajorsClass() {
        super();
    }

    public MajorsClass(String id, String name, int totalStudent, List<String> listStudents) {
        this.id = id;
        this.name = name;
        this.totalStudent = totalStudent;
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
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public List<String> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<String> listStudents) {
        this.listStudents = listStudents;
    }
}
