package com.example.fellow.telegrambot;

import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.SendMessage;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelegramClient {

    public final static String GETUPDATES_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/getUpdates";
    public final static String SENDMESSAGE_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/sendMessage";

    private String get (String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public GetUpdates getUpdates() throws IOException {
        Gson gson = new Gson();
        String json = get(GETUPDATES_URL);
        GetUpdates getUpdates = gson.fromJson(json, GetUpdates.class);
        return getUpdates;
    }

    public SendMessage sendMessage(String chatId, String message) throws IOException {
        Gson gson = new Gson();
        String json = get(SENDMESSAGE_URL + "?chat_id=" + chatId + "&text=" + message);
        SendMessage sendMessage = gson.fromJson(json, SendMessage.class);
        return sendMessage;
    }

}
