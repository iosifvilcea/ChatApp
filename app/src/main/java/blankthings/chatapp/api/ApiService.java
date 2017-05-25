package blankthings.chatapp.api;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import blankthings.chatapp.api.models.account.Account;
import blankthings.chatapp.api.models.chats.Chat;
import blankthings.chatapp.api.models.chats.ChatMessage;
import blankthings.chatapp.api.models.chats.Messages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiService
 *   - Sets up retrofit and our api endpoints.
 *
 * Created by iosifvilcea on 5/21/17.
 */
public class ApiService {

    private Gson gson;
    private Endpoints endpoints;
    private Retrofit retrofit;


    public ApiService() {
        this(null);
    }


    public ApiService(@Nullable final String url) {
        gson = new Gson();

        String baseUrl = Endpoints.BASE_URL;
        if (!TextUtils.isEmpty(url)) {
            baseUrl = url;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        endpoints = retrofit.create(Endpoints.class);
    }


    Endpoints make() {
        return endpoints;
    }


    public void getLogin(final String email, final String password, final Callback<Account> callback) {
        final Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        final String body = gson.toJson(map);

        final Call<Account> loginCall = make().login(body);
        loginCall.enqueue(callback);
    }


    public void logout(final String auth) {
        make().logout(auth)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {}

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {}
                });
    }


    public void createUser(final String email, final String pass,
                           final String name, final Callback<Account> callback) {
        final Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("password", pass);
        map.put("password_confirmation", pass);

        final String body = gson.toJson(map);
        make().createUser(body)
                .enqueue(callback);
    }


    public void fetchChats(final String auth, final int page, final int pageLimit, Callback<Chat> callback) {
        make().chats(auth, page, pageLimit)
                .enqueue(callback);
    }


    public void fetchChatMessages(final String auth, final int chatId,
                                  final int page, final int pageLimit, Callback<Messages> callback) {
        make().chatMessages(auth, chatId, page, pageLimit)
                .enqueue(callback);
    }


    public void sendMessage(final String message, final String auth, final int chatId,
                            final Callback<ChatMessage> callback) {
        final Map<String, String> map = new HashMap<>();
        map.put("message", message);
        final String body = gson.toJson(map);
        make().sendMessage(auth, chatId, body)
                .enqueue(callback);
    }
}
