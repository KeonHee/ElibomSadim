package kr.co.midas.midasmobile.base.network;

import kr.co.midas.midasmobile.base.domain.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static kr.co.midas.midasmobile.base.define.Define.HOST_URL;

public class LoginService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public interface Auth {
        @FormUrlEncoded
        @POST("user/login.php")
        Call<User> login(@Field("email") String email, @Field("pw") String pw);
    }
}

