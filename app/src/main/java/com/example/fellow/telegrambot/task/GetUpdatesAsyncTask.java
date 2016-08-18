package com.example.fellow.telegrambot.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fellow.telegrambot.TelegramAdapter;
import com.example.fellow.telegrambot.R;
import com.example.fellow.telegrambot.TelegramClient;
import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.Message;
import com.example.fellow.telegrambot.dto.MessageInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetUpdatesAsyncTask extends AsyncTask<Void, Void, List<MessageInfo>> {

    private Activity activity;

    public GetUpdatesAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<MessageInfo> doInBackground(Void... params) {
        TelegramClient telegramClient = new TelegramClient();
        GetUpdates getUpdates;
        String photoPath;
        List<MessageInfo> messageInfoList = new ArrayList<>();
        try {
            getUpdates = telegramClient.getUpdates();
            for(int i = 0; i < getUpdates.getResult().size(); i++) {
                photoPath = telegramClient.getPhotoPath(getUpdates.getResult().get(i).getMessage().getFrom().getId());
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setChatId(getUpdates.getResult().get(i).getMessage().getChat().getId());
                messageInfo.setUserId(getUpdates.getResult().get(i).getMessage().getFrom().getId());
                messageInfo.setText(getUpdates.getResult().get(i).getMessage().getText());
                messageInfo.setFirstName(getUpdates.getResult().get(i).getMessage().getFrom().getFirst_name());
                messageInfo.setDate(getUpdates.getResult().get(i).getMessage().getDate());
                messageInfo.setPhotoPath(photoPath);
                messageInfoList.add(messageInfo);
            }
            //return telegramClient.getUpdates();
            return messageInfoList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<MessageInfo> messageInfoList) {
        super.onPostExecute(messageInfoList);
        if(messageInfoList != null) {
            ListView listView = (ListView) activity.findViewById(R.id.listViewGetUpdates);
            ((TelegramAdapter) listView.getAdapter()).getMessageInfoList().clear();
            ((TelegramAdapter) listView.getAdapter()).getMessageInfoList().addAll(messageInfoList);
            ((TelegramAdapter) listView.getAdapter()).notifyDataSetChanged();
        } else {
            Toast.makeText(activity, "Нет сообщений", Toast.LENGTH_LONG).show();
        }
    }
}
