package com.example.BETest.object;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Teacher extends Person {
    private String office;
    private String addressWork;
    private String classManage;
    private String listClassTeaching;

    public Teacher() {

    }

    public Teacher(String id, String firstName, String lastName, String email, String gender, String address, String office, String addressWork, String classManage, String listClassTeaching) {
        super(id, firstName, lastName, email, gender, address);
        this.office = office;
        this.addressWork = addressWork;
        this.classManage = classManage;
        this.listClassTeaching = listClassTeaching;
    }
}
