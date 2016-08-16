package com.example.fellow.telegrambot.dto.getphotos;

import java.util.List;

public class Result {

    private String total_count;
    private List<List<Photos>> photos;

    public String getTotal_count() {
        return total_count;
    }

    public List<List<Photos>> getPhotos() {
        return photos;
    }
}
