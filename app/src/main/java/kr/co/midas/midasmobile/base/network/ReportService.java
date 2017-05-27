package kr.co.midas.midasmobile.base.network;

import java.util.List;

import kr.co.midas.midasmobile.base.domain.Report;
import kr.co.midas.midasmobile.base.domain.ResponseData;
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

public interface ReportService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @FormUrlEncoded
    @GET("midas/report/read.php")
    Call<ResponseData<List<Report>>> getReportOne(@Query("id") int id);

    @GET("midas/report/list.php")
    Call<ResponseData<List<Report>>> getReportAll(@Query("idx") int idx);
}
