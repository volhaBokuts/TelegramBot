package com.example.fellow.telegrambot;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.Result;
import com.example.fellow.telegrambot.task.GetUpdatesTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final private String GET_UPDATES_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/getUpdates";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewGetUpdates);

        GetUpdatesTask task = new GetUpdatesTask();
        task.execute();

        GetUpdatesAdapter getUpdatesAdapter = new GetUpdatesAdapter(resultList, MainActivity.this);
        listView.setAdapter(getUpdatesAdapter);

    }
}
