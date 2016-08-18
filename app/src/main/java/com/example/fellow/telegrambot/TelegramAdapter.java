package com.example.fellow.telegrambot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fellow.telegrambot.dto.Message;
import com.example.fellow.telegrambot.dto.MessageInfo;
import com.example.fellow.telegrambot.dto.Result;

import java.util.List;

public class TelegramAdapter extends BaseAdapter {

    private List<MessageInfo> messageInfoList;
    private Context context;
    static String KEY = "chatId";

    public TelegramAdapter(List<MessageInfo> messageInfoList, Context context) {
        this.messageInfoList = messageInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messageInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MessageInfo messageInfo = messageInfoList.get(position);
        View messageInfoView;

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        messageInfoView = layoutInflater.inflate(R.layout.list_view_getupdate, parent, false);
        //((ImageView)messageInfoView.findViewById(R.id.imageViewAvatar)).
        ((TextView)messageInfoView.findViewById(R.id.textViewText)).setText(messageInfo.getText());
        ((TextView)messageInfoView.findViewById(R.id.textViewName)).setText(messageInfo.getFirstName());
        ((TextView)messageInfoView.findViewById(R.id.textViewDate)).setText(messageInfo.getDate());

        messageInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SendMessageActivity.class);
                intent.putExtra(KEY, messageInfo.getChatId());
                context.startActivity(intent);
            }
        });

        return messageInfoView;
    }

    public List<MessageInfo> getMessageInfoList() {
        return messageInfoList;
    }

}
