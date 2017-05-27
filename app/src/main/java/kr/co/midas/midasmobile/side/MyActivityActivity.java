package kr.co.midas.midasmobile.side;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.define.Define;
import kr.co.midas.midasmobile.base.domain.ResponseListData;
import kr.co.midas.midasmobile.base.domain.Voluntary;
import kr.co.midas.midasmobile.base.network.RecordService;
import kr.co.midas.midasmobile.base.utils.SharedPreferenceUtils;
import kr.co.midas.midasmobile.side.adapter.MyActivityAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kr.co.midas.midasmobile.base.define.Define.OK;


public class MyActivityActivity extends AppCompatActivity {

    @BindView(R.id.activity_list_rv)
    RecyclerView mRecordListView;

    private MyActivityAdapter mMyActivityAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);

        ButterKnife.bind(this);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecordListView.setLayoutManager(mLinearLayoutManager);

        mMyActivityAdapter = new MyActivityAdapter(this);
        mRecordListView.setAdapter(mMyActivityAdapter);
        mRecordListView.setHasFixedSize(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        long uid=SharedPreferenceUtils.getLongPreference(getApplicationContext(),Define.SHR_PREF_USER_ID_KEY,-1);
        if(uid!=-1){
            getRecordData("user", 2, 0);
            Log.d("MyActivity", "my uid : " + uid);
        }
    }

    private void getRecordData(String type, long id, int page ){
        RecordService recordService = RecordService.retrofit.create(RecordService.class);
        Call<ResponseListData<Voluntary>> call = recordService.getRecord(
                type,id,page,"all");

        call.enqueue(new Callback<ResponseListData<Voluntary>>() {
            @Override
            public void onResponse(Call<ResponseListData<Voluntary>> call, Response<ResponseListData<Voluntary>> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()== OK){
                        mMyActivityAdapter.setListData(response.body().getResult());
                        Log.d("MyActivity", "ok");
                    }else {
                        Log.d("MyActivity", "code error1");
                    }
                }else {
                    Log.d("MyActivity", "code error2");
                }
            }

            @Override
            public void onFailure(Call<ResponseListData<Voluntary>> call, Throwable t) {
                Log.d("MyActivity", "network error : " + t.getMessage());
                call.cancel();
            }
        });
    }
}
