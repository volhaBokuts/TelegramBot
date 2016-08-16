package com.example.fellow.telegrambot;

import com.example.fellow.telegrambot.dto.GetUpdates;
import com.example.fellow.telegrambot.dto.SendMessage;
import com.example.fellow.telegrambot.dto.getfile.GetFile;
import com.example.fellow.telegrambot.dto.getphotos.GetUserProfilePhotos;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelegramClient {

    public final static String API_KEY = "207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc";
    public final static String GETUPDATES_URL =
            "https://api.telegram.org/bot" + API_KEY + "/getUpdates";
    public final static String SENDMESSAGE_URL =
            "https://api.telegram.org/bot" + API_KEY + "/sendMessage";
    public final static String GET_USER_PROFILE_PHOTOS_URL = "https://api.telegram.org/bot" + API_KEY + "/getUserProfilePhotos";
    public final static String GETFILE_URL = "https://api.telegram.org/bot" + API_KEY + "/getFile";
    public final static String GETPHOTO_URL = "https://api.telegram.org/file/bot" + API_KEY + "/";

    //https://api.telegram.org/file/bot207051102:AAHN1chK2w6KGwc-WyocuxaE7Lkl4H40CQc/photo/file_0.jpg

    private String get (String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public GetUpdates getUpdates() throws IOException {
        Gson gson = new Gson();
        String json = get(GETUPDATES_URL);
        GetUpdates getUpdates = gson.fromJson(json, GetUpdates.class);
        return getUpdates;
    }

    public SendMessage sendMessage(String chatId, String message) throws IOException {
        Gson gson = new Gson();
        String json = get(SENDMESSAGE_URL + "?chat_id=" + chatId + "&text=" + message);
        SendMessage sendMessage = gson.fromJson(json, SendMessage.class);
        return sendMessage;
    }

    public GetUserProfilePhotos getUserProfilePhotos(String userId) throws IOException {
        Gson gson = new Gson();
        String json = get(GET_USER_PROFILE_PHOTOS_URL + "?user_id=" + userId);
        GetUserProfilePhotos getUserProfilePhotos = gson.fromJson(json, GetUserProfilePhotos.class);
        return getUserProfilePhotos;
    }

    public GetFile getFile(String fileId) throws IOException {
        Gson gson = new Gson();
        String json = get(GETFILE_URL + "?file_id=" + fileId);
        GetFile getFile = gson.fromJson(json, GetFile.class);
        return getFile;
    }

    public String getPhoto(String filePath) throws IOException {
        return GETPHOTO_URL + filePath;
    }

    public String getPhotoPath(String userId) throws IOException {
        getUserProfilePhotos(userId);
        getFile(getUserProfilePhotos(userId).getResult().getPhotos().get(0).get(0).getFile_id());
        return getPhoto(getFile(getUserProfilePhotos(userId).getResult().getPhotos().get(0).get(0).getFile_id()).getResult().getFile_path());
    }

}
