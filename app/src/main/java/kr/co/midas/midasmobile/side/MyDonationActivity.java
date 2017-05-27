package kr.co.midas.midasmobile.side;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.define.Define;
import kr.co.midas.midasmobile.base.domain.Donation;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.network.RecordService;
import kr.co.midas.midasmobile.base.utils.SharedPreferenceUtils;
import kr.co.midas.midasmobile.side.adapter.MyDonationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_CUR_POINT_KEY;


public class MyDonationActivity extends AppCompatActivity  implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    @BindView(R.id.current_point_tv)
    TextView mCurrentPoint;

    @BindView(R.id.donation_list_rv)
    RecyclerView mDonationListView;

    private MyDonationAdapter mMyDonationAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donation);

        ButterKnife.bind(this);

        initLineChart();

        mLinearLayoutManager = new LinearLayoutManager(this);
        mDonationListView.setLayoutManager(mLinearLayoutManager);

        mMyDonationAdapter = new MyDonationAdapter(this);
        mDonationListView.setAdapter(mMyDonationAdapter);
        mDonationListView.setHasFixedSize(true);


        long point = SharedPreferenceUtils.getLongPreference(getApplicationContext(),SHR_PREF_CUR_POINT_KEY,-1);
        if(point!=-1){
            mCurrentPoint.setText(String.valueOf(point) + "점 ");
        }
        loadData();
    }

    private void loadData(){
        RecordService recordService = RecordService.retrofit.create(RecordService.class);
        Call<ResponseData<List<Donation>>> call = recordService.getDonations("user",2,0);
        call.enqueue(new Callback<ResponseData<List<Donation>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<Donation>>> call, Response<ResponseData<List<Donation>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode()== Define.OK){
                        List<Donation> list = response.body().getResult();

                        setData(list);
                        mMyDonationAdapter.setListData(list);
                    }else {

                    }
                }else {

                }


            }

            @Override
            public void onFailure(Call<ResponseData<List<Donation>>> call, Throwable t) {

            }
        });
    }

    private void initLineChart() {
        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

      //  IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
      //  xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
      //  xAxis.setValueFormatter(xAxisFormatter);

        //IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        //leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
       // leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
       // rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
       //rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart

        // setting data
        mSeekBarY.setProgress(50);
        mSeekBarX.setProgress(12);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);

    }

    private void setData(List<Donation> donationList) {
        if(donationList==null || donationList.isEmpty()) return;

        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        float[] months ={0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
        for(Donation donation : donationList){
            int idx = Integer.parseInt(donation.getDonate_at().substring(5,7));
            months[idx] += donation.getPoint();
        }

        for (int i = 0; i < 12; i++) {
            yVals1.add(new BarEntry(i+1, months[i] ));

        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
