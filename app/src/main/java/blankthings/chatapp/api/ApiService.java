package blankthings.chatapp.api;

import android.support.annotation.Nullable;
import android.text.TextUtils;

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

    private Endpoints endpoints;
    private Retrofit retrofit;


    public ApiService() {
        this(null);
    }


    public ApiService(@Nullable final String url) {
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


    public void getLogin(final String email, final String password, final Callback<AccountData> callback) {
        final String body = String.format("{'email':'%s','password':'%s'}", email, password);
        final Call<AccountData> loginCall = make().login(body);
        loginCall.enqueue(callback);
    }


    public void logout(final String auth) {
        make().logout(auth)
                .enqueue(new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) {}
            @Override public void onFailure(Call<Void> call, Throwable t) {}
        });
    }
}
