package kr.co.midas.midasmobile.base.network;

import android.util.Log;

import java.util.List;

import kr.co.midas.midasmobile.base.domain.Issue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 2017-05-27.
 */

public class GithubIssue {

    public final static String GITHUB_HOST_URL = "https://api.github.com/";

    /* GitHub Issue List */
    /* GitHub Issue List */
    public interface GItHubIssue {
        @GET("/repos/{org}/{prjName}/issues")
        Call<List<Issue>> gitIssues(@Path("org") String org, @Path("prjName") String prjName);
    }

    public interface RetrofitCallback {
        // 생성시의 Callback
        void success(List<Issue> gitIssues);

        // 실패시의 Callback
        void error(Throwable throwable);
    }

    /* Get Method Http 통신, Async */
    public static void getGitIssueListAsync(String org, String prjName, final RetrofitCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GItHubIssue gitHub = retrofit.create(GItHubIssue.class);

        Call<List<Issue>> call = gitHub.gitIssues(org,prjName);

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if(response.isSuccessful()){
                    List<Issue> gitIssues = response.body();
                    callback.success(gitIssues);
                }else{
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });

    }
}
