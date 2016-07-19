package com.example.fellow.telegrambot.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.fellow.telegrambot.MainActivity;
import com.example.fellow.telegrambot.TelegramClient;
import com.example.fellow.telegrambot.dto.SendMessage;

import java.io.IOException;

public class SendMessageAsyncTask extends AsyncTask<Void, Void, SendMessage> {

    private String chatId;
    private String message;
    private Activity activity;

    public SendMessageAsyncTask(String chatId, String message, Activity activity) {
        this.chatId = chatId;
        this.message = message;
        this.activity = activity;
    }

    @Override
    protected SendMessage doInBackground(Void... params) {
        TelegramClient telegramClient = new TelegramClient();
        try {
            return telegramClient.sendMessage(chatId, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SendMessage sendMessage) {
        super.onPostExecute(sendMessage);
        if(sendMessage != null) {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "Ошибка", Toast.LENGTH_LONG).show();
        }
    }
}
