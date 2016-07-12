package com.example.fellow.telegrambot;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelegramClient {

    OkHttpClient client;

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

}
