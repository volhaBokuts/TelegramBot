package com.example.fellow.telegrambot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fellow.telegrambot.dto.Result;

import java.util.List;

public class GetUpdatesAdapter extends BaseAdapter {

    private List<Result> resultList;
    private Context context;

    public GetUpdatesAdapter(List<Result> resultList, Context context) {
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
        Result result = resultList.get(position);
        View resultView;

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resultView = layoutInflater.inflate(R.layout.list_view_getupdate, parent, false);
        ((TextView)resultView.findViewById(R.id.textViewText)).setText(result.getMessage().getText());
        ((TextView)resultView.findViewById(R.id.textViewName)).setText(result.getMessage().getFrom().getFirst_name());
        ((TextView)resultView.findViewById(R.id.textViewDate)).setText(result.getMessage().getDate().toString());

        return resultView;
    }

}
