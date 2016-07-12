package com.example.fellow.telegrambot;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final private String GET_UPDATES_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/getUpdates";
    private ListView listView;
    private String json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewGetUpdates);

        final AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    TelegramClient telegramClient = new TelegramClient();
                    json = telegramClient.getJson(GET_UPDATES_URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                GetUpdates getUpdates = gson.fromJson(json, GetUpdates.class);
                List<Result> resultList = getUpdates.getResult();
                GetUpdatesAdapter getUpdatesAdapter = new GetUpdatesAdapter(resultList, MainActivity.this);
                listView.setAdapter(getUpdatesAdapter);
            }
        };

        asyncTask.execute();

    }
}
