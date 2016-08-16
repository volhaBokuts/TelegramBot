package com.example.fellow.telegrambot.task;

import android.os.AsyncTask;

import com.example.fellow.telegrambot.TelegramClient;
import com.example.fellow.telegrambot.dto.getfile.GetFile;

import java.io.IOException;

public class GetFileAsyncTask extends AsyncTask<Void, Void, GetFile> {

    private String fileId;

    @Override
    protected GetFile doInBackground(Void... params) {
        TelegramClient telegramClient = new TelegramClient();
        try {
            return telegramClient.getFile(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
