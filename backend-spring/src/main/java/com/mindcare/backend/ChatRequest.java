package com.mindcare.backend;

public class ChatRequest {
    private String userMessage;
    private String userId;

    public String getUserMessage() { return userMessage; }
    public void setUserMessage(String userMessage) { this.userMessage = userMessage; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
