package blankthings.chatapp.api;

import blankthings.chatapp.api.models.account.Account;
import blankthings.chatapp.api.models.chats.Chat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Endpoints for our Retrofit Client.
 *
 * Created by iosifvilcea on 5/21/17.
 */

public interface Endpoints {

    String BASE_URL = "https://private-93240c-oracodechallenge.apiary-mock.com";

    @POST("auth/login")
    Call<Account> login(@Body String body);


    @GET("auth/logout")
    Call<Void> logout(@Header("Authorization") String header);


    @POST("auth/users")
    Call<Account> createUser(@Body String body);


    @GET("chats")
    Call<Chat> chats(@Header("Authorization") String header,
                            @Query("page") Integer page,
                            @Query("limit") Integer limit);

}
