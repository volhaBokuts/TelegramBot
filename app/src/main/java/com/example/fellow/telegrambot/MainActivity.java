package com.example.fellow.telegrambot;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fellow.telegrambot.dto.GetUpdates;
import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final private String GET_UPDATES_URL =
            "https://api.telegram.org/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/getUpdates";
    ListView listView;
    private String json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewGetUpdates);

        final AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    TelegramClient telegramClient = new TelegramClient();
                    json = telegramClient.getJson(GET_UPDATES_URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                GetUpdates getUpdates = gson.fromJson(json, GetUpdates.class);
            }
        };

        asyncTask.execute();

        /*List<String> stringList = new ArrayList<String>();
stringList.add("name: " + bookingRecord.getName());
stringList.add("surname: " + bookingRecord.getSurname());
stringList.add("date: " + bookingRecord.getDate());
stringList.add("time: " + bookingRecord.getTime());
stringList.add("nights: " + bookingRecord.getNights());
stringList.add("gender: " + bookingRecord.getGender());
stringList.add("city: " + bookingRecord.getCity());
stringList.add("wish: " + bookingRecord.getWish());

ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
android.R.layout.simple_list_item_1,
stringList);

listView.setAdapter(stringArrayAdapter);*/

    }
}
