package com.example.messaginglistviewhw;

public class Message {

    private String message;
    private String sender;
    private String time;

    public Message(String message, String sender, String time) {
        this.message = message;
        this.sender = sender;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }
}
