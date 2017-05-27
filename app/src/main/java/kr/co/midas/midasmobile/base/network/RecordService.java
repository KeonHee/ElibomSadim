package kr.co.midas.midasmobile.base.network;


import java.util.List;

import kr.co.midas.midasmobile.base.domain.Donation;
import kr.co.midas.midasmobile.base.domain.Rank;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.domain.Voluntary;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static kr.co.midas.midasmobile.base.define.Define.HOST_URL;

public interface RecordService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("midas/voluntaryRecord/list.php")
    Call<ResponseData<List<Voluntary>>> getRecord(@Query("type") String type,
                                                  @Query("id") long id,
                                                  @Query("idx") int page,
                                                  @Query("state") String state
                                         );

    @GET("midas/rank/rank.php")
    Call<ResponseData<Rank>> getRanks();

    @GET("midas/donation/list.php")
    Call<ResponseData<List<Donation>>> getDonations(@Query("type") String type,
                                              @Query("id") long id,
                                              @Query("idx") int page);
}
