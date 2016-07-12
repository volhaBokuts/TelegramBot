package com.example.fellow.telegrambot;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final private String GET_UPDATES_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/getUpdates";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String json = null;
                try {
                    TelegramClient telegramClient = new TelegramClient();
                    json = telegramClient.getJson(GET_UPDATES_URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return json;
            }
        };

    }
}
