package com.hospital.hospital_management.dto;

public class ErrorResponse {
    private String error;
    private String message;
    private String timestamp;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    // Getters and setters
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}