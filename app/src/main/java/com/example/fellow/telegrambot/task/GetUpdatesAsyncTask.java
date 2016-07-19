package com.example.fellow.telegrambot.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fellow.telegrambot.TelegramAdapter;
import com.example.fellow.telegrambot.R;
import com.example.fellow.telegrambot.TelegramClient;
import com.example.fellow.telegrambot.dto.GetUpdates;

import java.io.IOException;

public class GetUpdatesAsyncTask extends AsyncTask<Void, Void, GetUpdates> {

    private Activity activity;

    public GetUpdatesAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected GetUpdates doInBackground(Void... params) {
        TelegramClient telegramClient = new TelegramClient();
        try {
            return telegramClient.getUpdates();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(GetUpdates getUpdates) {
        super.onPostExecute(getUpdates);
        if(getUpdates != null) {
            ListView listView = (ListView) activity.findViewById(R.id.listViewGetUpdates);
            ((TelegramAdapter) listView.getAdapter()).getResultList().clear();
            ((TelegramAdapter) listView.getAdapter()).getResultList().addAll(getUpdates.getResult());
            ((TelegramAdapter) listView.getAdapter()).notifyDataSetChanged();
        } else {
            Toast.makeText(activity, "Нет сообщений", Toast.LENGTH_LONG).show();
        }
    }
}