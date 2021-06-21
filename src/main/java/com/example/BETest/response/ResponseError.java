package com.example.BETest.response;

public class ResponseError extends BaseResponse {
    private String message;
    private String authorization;

    public ResponseError(int statusCode) {
        super(statusCode);
    }

    public ResponseError(int statusCode, String message, String authorization) {
        super(statusCode);
        this.message = message;
        this.authorization = authorization;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
