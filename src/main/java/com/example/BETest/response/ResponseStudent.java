package com.example.BETest.response;
import java.util.List;

public class ResponseStudent<T> extends BaseResponse {
    private List<T> data;

    public ResponseStudent(int statusCode, List<T> data) {
        super(statusCode);
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
