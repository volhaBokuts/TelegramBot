package com.example.fellow.telegrambot.dto;

import java.util.Date;

public class Message {

    private String message_id;
    private From from;
    private Chat chat;
    private String date;
    private String text;

    public String getMessage_id() {
        return message_id;
    }

    public From getFrom() {
        return from;
    }

    public Chat getChat() {
        return chat;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

}
