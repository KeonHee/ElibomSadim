package kr.co.midas.midasmobile.side;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Record;
import kr.co.midas.midasmobile.base.domain.Voluntary;
import kr.co.midas.midasmobile.side.adapter.MyActivityAdapter;


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

        List<Record> fakeData = new ArrayList<>();
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));
        fakeData.add(new Record(1,"zz",new Voluntary(
                0,"강남","제목","내용","교육", Calendar.getInstance().getTime(),6,5,5
                , null),null,null));

        mMyActivityAdapter.setListData(fakeData);
    }
}
