package com.mindcare.backend;

public class ChatResponse {
    private String reply;
    private Boolean distress;

    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }
    public Boolean getDistress() { return distress; }
    public void setDistress(Boolean distress) { this.distress = distress; }
}
