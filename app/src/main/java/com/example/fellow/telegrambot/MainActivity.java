package com.example.fellow.telegrambot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.fellow.telegrambot.dto.Result;
import com.example.fellow.telegrambot.task.GetUpdatesAsyncTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewGetUpdates);

        //new GetUpdatesAsyncTask(this).execute();

        TelegramAdapter telegramAdapter = new TelegramAdapter(new ArrayList<Result>(), this);
        listView.setAdapter(telegramAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetUpdatesAsyncTask(this).execute();
    }
}
