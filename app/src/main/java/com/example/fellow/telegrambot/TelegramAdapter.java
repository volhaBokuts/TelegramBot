package com.example.fellow.telegrambot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fellow.telegrambot.dto.Result;

import java.util.List;

public class TelegramAdapter extends BaseAdapter {

    private List<Result> resultList;
    private Context context;
    static String KEY = "chatId";

    public TelegramAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Result result = resultList.get(position);
        View resultView;

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resultView = layoutInflater.inflate(R.layout.list_view_getupdate, parent, false);
        ((TextView)resultView.findViewById(R.id.textViewText)).setText(result.getMessage().getText());
        ((TextView)resultView.findViewById(R.id.textViewName)).setText(result.getMessage().getFrom().getFirst_name());
        ((TextView)resultView.findViewById(R.id.textViewDate)).setText(result.getMessage().getDate());

        resultView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SendMessageActivity.class);
                intent.putExtra(KEY, result.getMessage().getChat().getId());
                context.startActivity(intent);
            }
        });

        return resultView;
    }

    public List<Result> getResultList() {
        return resultList;
    }

}
