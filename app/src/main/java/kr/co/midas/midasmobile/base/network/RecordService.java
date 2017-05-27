package kr.co.midas.midasmobile.base.network;


import kr.co.midas.midasmobile.base.domain.ResponseListData;
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

    //http://kyuhwan.com:8080/midas/voluntaryRecord/list.php?type=user&id=2&idx=0&state=all
    @GET("midas/voluntaryRecord/list.php")
    Call<ResponseListData<Voluntary>> getRecord(@Query("type") String type,
                                                @Query("id") long id,
                                                @Query("idx") int page,
                                                @Query("state") String state
                                         );
}
