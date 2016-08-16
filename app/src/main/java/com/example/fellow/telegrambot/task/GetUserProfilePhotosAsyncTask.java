package com.example.fellow.telegrambot.task;

import android.os.AsyncTask;

import com.example.fellow.telegrambot.TelegramClient;
import com.example.fellow.telegrambot.dto.getphotos.GetUserProfilePhotos;

import java.io.IOException;

public class GetUserProfilePhotosAsyncTask extends AsyncTask<Void, Void, GetUserProfilePhotos> {

    private String userId;

    public GetUserProfilePhotosAsyncTask(String userId) {
        this.userId = userId;
    }

    @Override
    protected GetUserProfilePhotos doInBackground(Void... params) {
        TelegramClient telegramClient = new TelegramClient();
        try {
            return telegramClient.getUserProfilePhotos(userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
