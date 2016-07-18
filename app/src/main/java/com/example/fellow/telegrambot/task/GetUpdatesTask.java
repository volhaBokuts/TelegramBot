package com.example.fellow.telegrambot.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.fellow.telegrambot.GetUpdatesAdapter;
import com.example.fellow.telegrambot.MainActivity;
import com.example.fellow.telegrambot.TelegramClient;
import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class GetUpdatesTask extends AsyncTask<Void, Void, List<Result>> {

    private Activity activity;
    final private String GET_UPDATES_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/getUpdates";
    private String json = "";
    private List<Result> resultList;

    public GetUpdatesTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<Result> doInBackground(Void... params) {
        TelegramClient telegramClient = new TelegramClient();
        try {
            json = telegramClient.getJson(GET_UPDATES_URL);
            Gson gson = new Gson();
            GetUpdates getUpdates = gson.fromJson(json, GetUpdates.class);
            resultList = getUpdates.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    protected void onPostExecute(List<Result> results) {
        super.onPostExecute(results);
        GetUpdatesAdapter getUpdatesAdapter = new GetUpdatesAdapter(results, );
        listView.setAdapter(getUpdatesAdapter);
    }
}
