package com.example.BETest.response;

public class ResponseMessage extends BaseResponse {
    private String message;

    public ResponseMessage(int statusCode, String message) {
        super(statusCode);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
