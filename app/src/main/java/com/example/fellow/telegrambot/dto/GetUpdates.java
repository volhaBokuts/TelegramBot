package com.example.fellow.telegrambot.dto;

import java.util.List;

public class GetUpdates {

    private boolean ok;
    private List<Result> result;

    public boolean getOk() {
        return ok;
    }

    public List<Result> getResult() {
        return result;
    }

}
