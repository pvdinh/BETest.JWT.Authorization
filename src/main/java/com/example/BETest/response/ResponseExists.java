package com.example.BETest.response;

public class ResponseExists extends BaseResponse {
    private String message;

    public ResponseExists(int statusCode, String message) {
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
