package kr.co.midas.midasmobile.base.network;

import java.util.List;

import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.domain.Voluntary;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static kr.co.midas.midasmobile.base.define.Define.HOST_URL;

/**
 * Created by user on 2017-05-28.
 */

public interface VoluntaryService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @FormUrlEncoded
    @GET("midas/voluntary/read.php")
    Call<ResponseData<List<Voluntary>>> getVoluntaryOne(@Query("vid") long vid);

    @GET("midas/voluntary/list.php")
    Call<ResponseData<List<Voluntary>>> getVoluntaryAll(@Query("idx") long idx);

}
