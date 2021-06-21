package com.example.BETest.response;
import com.example.BETest.object.Student;

import java.util.List;

public class ResponseStudent extends BaseResponse {
    private List<Student> data;

    public ResponseStudent(int statusCode, List<Student> data) {
        super(statusCode);
        this.data = data;
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }
}
