package com.example.fellow.telegrambot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fellow.telegrambot.task.SendMessageAsyncTask;

import java.io.IOException;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    EditText sendMessage;
    Button sendMessageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        sendMessage = (EditText) findViewById(R.id.editTextSendMessage);
        sendMessageBtn = (Button) findViewById(R.id.btnSendMessage);
    }

    @Override
    public void onClick(View v) {
        String chatId = getIntent().getStringExtra(TelegramAdapter.KEY);
        String message = sendMessage.getText().toString();

        SendMessageAsyncTask sendMessageAsyncTask = new SendMessageAsyncTask(chatId, message, this);
        sendMessageAsyncTask.execute();

    }
}
