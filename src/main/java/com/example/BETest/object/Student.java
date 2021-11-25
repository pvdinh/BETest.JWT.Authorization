package com.example.BETest.object;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student extends Person {
    public Student() {
    }

    public Student(String id, String firstName, String lastName, String email, String gender, String address) {
        super(id, firstName, lastName, email, gender, address);
    }
}
