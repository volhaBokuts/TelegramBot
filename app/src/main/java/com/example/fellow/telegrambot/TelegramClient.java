package com.example.fellow.telegrambot;

import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelegramClient {

    public final static String API = "207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc";
    public final static String GETUPDATES_URL = "https://api.telegram.org/bot" + API + "/getUpdates";

    private OkHttpClient client;

    public TelegramClient() {
        this.client = new OkHttpClient();;
    }

    private String GET (String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getJson (String url) throws IOException {
        return GET(url);
    }

    public GetUpdates getUpdates() throws IOException {
        Gson gson = new Gson();
        String json = getJson(GETUPDATES_URL);
        GetUpdates getUpdates = gson.fromJson(json, GetUpdates.class);
        return getUpdates;
    }

    public List<Result> getResultList() throws IOException {
        return getUpdates().getResult();
    }

}
