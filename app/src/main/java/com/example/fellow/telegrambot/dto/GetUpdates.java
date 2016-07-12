package com.example.fellow.telegrambot.dto;

import java.util.List;

public class GetUpdates {

    private String ok;
    private List<Result> result;

    public String getOk() {
        return ok;
    }

    public List<Result> getResult() {
        return result;
    }

}
