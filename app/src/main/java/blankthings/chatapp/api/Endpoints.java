package blankthings.chatapp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Endpoints for our Retrofit Client.
 *
 * Created by iosifvilcea on 5/21/17.
 */

public interface Endpoints {

    String BASE_URL = "https://private-93240c-oracodechallenge.apiary-mock.com";

    @POST("auth/login")
    Call<AccountData> getLogin(@Body String body);

}
