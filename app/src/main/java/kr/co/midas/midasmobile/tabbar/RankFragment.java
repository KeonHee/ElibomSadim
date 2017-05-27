package kr.co.midas.midasmobile.tabbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Rank;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.domain.Section;
import kr.co.midas.midasmobile.base.domain.Team;
import kr.co.midas.midasmobile.base.domain.User;
import kr.co.midas.midasmobile.base.network.RecordService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kr.co.midas.midasmobile.base.define.Define.OK;


public class RankFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
		OnChartValueSelectedListener {

	private String[] mMonths = new String[]{
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
	};

	private String[] mSection = new String[]{
			"Party A", "Party B", "Party C", "Party D"
	};

	/**
	 * Section PieChart
	 */
	private PieChart mChart;
	private SeekBar mSeekBarX, mSeekBarY;
	private TextView tvX, tvY;

	/**
	 * Total HorizontalChart
	 */
	protected HorizontalBarChart mHoizonChart;
	private SeekBar mHoizonSeekBarX, mHoizonSeekBarY;
	private TextView tvHoizonX, tvHoizonY;

	/**
	 * Team Rank
	 */
	private View mTeamRankView;
	private TextView mTeamRankNum1;
	private TextView mTeamRankName1;
	private TextView mTeamRankPoint1;
	private TextView mTeamRankNum2;
	private TextView mTeamRankName2;
	private TextView mTeamRankPoint2;
	private TextView mTeamRankNum3;
	private TextView mTeamRankName3;
	private TextView mTeamRankPoint3;
	private TextView mTeamRankNum4;
	private TextView mTeamRankName4;
	private TextView mTeamRankPoint4;
	private TextView mTeamRankNum5;
	private TextView mTeamRankName5;
	private TextView mTeamRankPoint5;

	/**
	 * User Rank
	 */
	private View mUserRankView;
	private TextView mUserRankNum1;
	private TextView mUserRankName1;
	private TextView mUserRankPoint1;
	private TextView mUserRankNum2;
	private TextView mUserRankName2;
	private TextView mUserRankPoint2;
	private TextView mUserRankNum3;
	private TextView mUserRankName3;
	private TextView mUserRankPoint3;
	private TextView mUserRankNum4;
	private TextView mUserRankName4;
	private TextView mUserRankPoint4;
	private TextView mUserRankNum5;
	private TextView mUserRankName5;
	private TextView mUserRankPoint5;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_rank, container, false);
		initHorizontalChart(v);
		initPieChart(v);
		initTeamRank(v);
		initUserRank(v);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadData();
	}

	private void loadData(){
		RecordService recordService = RecordService.retrofit.create(RecordService.class);
		Call<ResponseData<Rank>> call= recordService.getRanks();
		call.enqueue(new Callback<ResponseData<Rank>>() {
			@Override
			public void onResponse(Call<ResponseData<Rank>> call, Response<ResponseData<Rank>> response) {
				if(response.isSuccessful()){
					if(response.body().getCode()== OK){
						Rank rank = response.body().getResult();
						bindTeamRankView(rank.getTeams());
						bindUserRankView(rank.getUsers());

						int totalPoint=0;
						for(Section section : rank.getSections()) {
							totalPoint+=section.getPoint();
						}
						setPieData(rank.getSections(),totalPoint);

						int totalDonation = (int) rank.getTotal();
						setHorizonData(totalPoint/1000,totalPoint,totalDonation);
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseData<Rank>> call, Throwable t) {

			}
		});
	}

	private void initTeamRank(View rootView) {
		mTeamRankView = rootView.findViewById(R.id.team_rank_layout_cardview);
		mTeamRankNum1 = (TextView) mTeamRankView.findViewById(R.id.rank_num1);
		mTeamRankName1 = (TextView) mTeamRankView.findViewById(R.id.name1);
		mTeamRankPoint1 = (TextView) mTeamRankView.findViewById(R.id.point1);
		mTeamRankNum2 = (TextView) mTeamRankView.findViewById(R.id.rank_num2);
		mTeamRankName2 = (TextView) mTeamRankView.findViewById(R.id.name2);
		mTeamRankPoint2 = (TextView) mTeamRankView.findViewById(R.id.point2);
		mTeamRankNum3 = (TextView) mTeamRankView.findViewById(R.id.rank_num3);
		mTeamRankName3 = (TextView) mTeamRankView.findViewById(R.id.name3);
		mTeamRankPoint3 = (TextView) mTeamRankView.findViewById(R.id.point3);
		mTeamRankNum4 = (TextView) mTeamRankView.findViewById(R.id.rank_num4);
		mTeamRankName4 = (TextView) mTeamRankView.findViewById(R.id.name4);
		mTeamRankPoint4 = (TextView) mTeamRankView.findViewById(R.id.point4);
		mTeamRankNum5 = (TextView) mTeamRankView.findViewById(R.id.rank_num5);
		mTeamRankName5 = (TextView) mTeamRankView.findViewById(R.id.name5);
		mTeamRankPoint5 = (TextView) mTeamRankView.findViewById(R.id.point5);
	}

	private void initUserRank(View rootView) {
		mUserRankView = rootView.findViewById(R.id.user_rank_layout_cardview);
		mUserRankNum1 = (TextView) mUserRankView.findViewById(R.id.rank_num1);
		mUserRankName1 = (TextView) mUserRankView.findViewById(R.id.name1);
		mUserRankPoint1 = (TextView) mUserRankView.findViewById(R.id.point1);
		mUserRankNum2 = (TextView) mUserRankView.findViewById(R.id.rank_num2);
		mUserRankName2 = (TextView) mUserRankView.findViewById(R.id.name2);
		mUserRankPoint2 = (TextView) mUserRankView.findViewById(R.id.point2);
		mUserRankNum3 = (TextView) mUserRankView.findViewById(R.id.rank_num3);
		mUserRankName3 = (TextView) mUserRankView.findViewById(R.id.name3);
		mUserRankPoint3 = (TextView) mUserRankView.findViewById(R.id.point3);
		mUserRankNum4 = (TextView) mUserRankView.findViewById(R.id.rank_num4);
		mUserRankName4 = (TextView) mUserRankView.findViewById(R.id.name4);
		mUserRankPoint4 = (TextView) mUserRankView.findViewById(R.id.point4);
		mUserRankNum5 = (TextView) mUserRankView.findViewById(R.id.rank_num5);
		mUserRankName5 = (TextView) mUserRankView.findViewById(R.id.name5);
		mUserRankPoint5 = (TextView) mUserRankView.findViewById(R.id.point5);
	}

	private void bindTeamRankView(List<Team> teams) {
		mTeamRankNum1.setText("1등");
		mTeamRankName1.setText(teams.get(0).getTeamName());
		mTeamRankPoint1.setText(String.valueOf(teams.get(0).getPoint()) + " 점");
		mTeamRankNum2.setText("2등");
		mTeamRankName2.setText(teams.get(1).getTeamName());
		mTeamRankPoint2.setText(String.valueOf(teams.get(1).getPoint()) + " 점");
		mTeamRankNum3.setText("3등");
		mTeamRankName3.setText(teams.get(2).getTeamName());
		mTeamRankPoint3.setText(String.valueOf(teams.get(2).getPoint()) + " 점");
		mTeamRankNum4.setText("4등");
		mTeamRankName4.setText(teams.get(3).getTeamName());
		mTeamRankPoint4.setText(String.valueOf(teams.get(3).getPoint()) + " 점");
		mTeamRankNum5.setText("5등");
		mTeamRankName5.setText(teams.get(4).getTeamName());
		mTeamRankPoint5.setText(String.valueOf(teams.get(4).getPoint()) + " 점");
	}

	private void bindUserRankView(List<User> user) {
		mUserRankNum1.setText("1등");
		mUserRankName1.setText(user.get(0).getUserName());
		mUserRankPoint1.setText(String.valueOf(user.get(0).getPoint()) + " 점");
		mUserRankNum2.setText("2등");
		mUserRankName2.setText(user.get(1).getUserName());
		mUserRankPoint2.setText(String.valueOf(user.get(1).getPoint()) + " 점");
		mUserRankNum3.setText("3등");
		mUserRankName3.setText(user.get(2).getUserName());
		mUserRankPoint3.setText(String.valueOf(user.get(2).getPoint()) + " 점");
		mUserRankNum4.setText("4등");
		mUserRankName4.setText(user.get(3).getUserName());
		mUserRankPoint4.setText(String.valueOf(user.get(3).getPoint()) + " 점");
		mUserRankNum5.setText("5등");
		mUserRankName5.setText(user.get(4).getUserName());
		mUserRankPoint5.setText(String.valueOf(user.get(4).getPoint()) + " 점");
	}

	private void initHorizontalChart(View view) {
		tvHoizonX = (TextView) view.findViewById(R.id.horizontal_tvXMax);
		tvHoizonY = (TextView) view.findViewById(R.id.horizontal_tvYMax);

		mHoizonSeekBarX = (SeekBar) view.findViewById(R.id.horizontal_seekBar1);
		mHoizonSeekBarY = (SeekBar) view.findViewById(R.id.horizontal_seekBar2);

		mHoizonChart = (HorizontalBarChart) view.findViewById(R.id.total_chart);
		mHoizonChart.setOnChartValueSelectedListener(this);
		// mChart.setHighlightEnabled(false);

		mHoizonChart.setDrawBarShadow(false);

		mHoizonChart.setDrawValueAboveBar(true);

		mHoizonChart.getDescription().setEnabled(false);

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mHoizonChart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		mHoizonChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		mHoizonChart.setDrawGridBackground(false);

		XAxis xl = mHoizonChart.getXAxis();
		xl.setPosition(XAxis.XAxisPosition.BOTTOM);
		//xl.setTypeface(mTfLight);
		xl.setDrawAxisLine(true);
		xl.setDrawGridLines(true);
		xl.setGranularity(10f);

		YAxis yl = mHoizonChart.getAxisLeft();
		//yl.setTypeface(mTfLight);
		yl.setDrawAxisLine(true);
		yl.setDrawGridLines(false);
		yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

		YAxis yr = mHoizonChart.getAxisRight();
		// yr.setTypeface(mTfLight);
		yr.setDrawAxisLine(true);
		yr.setDrawGridLines(true);
		yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

		mHoizonChart.setFitBars(true);
		mHoizonChart.animateY(2500);

		// setting data
		mHoizonSeekBarY.setProgress(50);
		mHoizonSeekBarX.setProgress(12);

		mHoizonSeekBarY.setOnSeekBarChangeListener(this);
		mHoizonSeekBarX.setOnSeekBarChangeListener(this);

		Legend l = mHoizonChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		l.setDrawInside(false);
		l.setFormSize(8f);
		l.setXEntrySpace(4f);
	}

	private void initPieChart(View view) {
		tvX = (TextView) view.findViewById(R.id.tvXMax);
		tvY = (TextView) view.findViewById(R.id.tvYMax);

		mSeekBarX = (SeekBar) view.findViewById(R.id.seekBar1);
		mSeekBarY = (SeekBar) view.findViewById(R.id.seekBar2);
		mSeekBarX.setProgress(4);
		mSeekBarY.setProgress(10);

		mChart = (PieChart) view.findViewById(R.id.section_chart);
		mChart.setUsePercentValues(true);
		mChart.getDescription().setEnabled(false);
		mChart.setExtraOffsets(5, 10, 5, 5);

		mChart.setDragDecelerationFrictionCoef(0.95f);

		//mChart.setCenterTextTypeface(mTfLight);
		mChart.setCenterText(generateCenterSpannableText());

		mChart.setDrawHoleEnabled(true);
		mChart.setHoleColor(Color.WHITE);

		mChart.setTransparentCircleColor(Color.WHITE);
		mChart.setTransparentCircleAlpha(110);

		mChart.setHoleRadius(58f);
		mChart.setTransparentCircleRadius(61f);

		mChart.setDrawCenterText(true);

		mChart.setRotationAngle(0);
		// enable rotation of the chart by touch
		mChart.setRotationEnabled(true);
		mChart.setHighlightPerTapEnabled(true);

		// mChart.setUnit(" €");
		// mChart.setDrawUnitsInChart(true);

		// add a selection listener
		mChart.setOnChartValueSelectedListener(this);

		mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
		// mChart.spin(2000, 0, 360);

		mSeekBarX.setOnSeekBarChangeListener(this);
		mSeekBarY.setOnSeekBarChangeListener(this);

		Legend l = mChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.VERTICAL);
		l.setDrawInside(false);
		l.setXEntrySpace(7f);
		l.setYEntrySpace(0f);
		l.setYOffset(0f);

		// entry label styling
		mChart.setEntryLabelColor(Color.WHITE);
		//mChart.setEntryLabelTypeface(mTfRegular);
		mChart.setEntryLabelTextSize(12f);
	}

	private SpannableString generateCenterSpannableText() {

		SpannableString s = new SpannableString("이 달의 봉사");
		s.setSpan(new RelativeSizeSpan(1.7f), 0, 7, 0);
		return s;
	}

	private void setHorizonData(int e1,int e2,int e3) {

		float barWidth = 5f;
		float spaceForBar = 10f;

		/**
		 * HorizontalChart Entry
		 */
		ArrayList<BarEntry> yVals1 = new ArrayList<>();
		yVals1.add(new BarEntry(1 * spaceForBar, e1, "t1"));
		ArrayList<BarEntry> yVals2 = new ArrayList<>();
		yVals2.add(new BarEntry(2 * spaceForBar, e2, "t2"));
		ArrayList<BarEntry> yVals3 = new ArrayList<>();
		yVals3.add(new BarEntry(3 * spaceForBar, e3, "t3"));

		ArrayList<Integer> colors1 = new ArrayList<>();
		ArrayList<Integer> colors2 = new ArrayList<>();
		ArrayList<Integer> colors3 = new ArrayList<>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors1.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors2.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors3.add(c);

		BarDataSet set1, set2, set3;

		if (mHoizonChart.getData() != null &&
				mHoizonChart.getData().getDataSetCount() > 0) {
			set1 = (BarDataSet) mHoizonChart.getData().getDataSetByIndex(0);
			set1.setValues(yVals1);
			set1.setColors(colors1);
			set2 = (BarDataSet) mHoizonChart.getData().getDataSetByIndex(1);
			set2.setValues(yVals2);
			set2.setColors(colors2);
			set3 = (BarDataSet) mHoizonChart.getData().getDataSetByIndex(2);
			set3.setValues(yVals3);
			set3.setColors(colors3);
			mHoizonChart.getData().notifyDataChanged();
			mHoizonChart.notifyDataSetChanged();
		} else {
			set1 = new BarDataSet(yVals1, "총 봉사 시간");
			set2 = new BarDataSet(yVals2, "총 봉사 포인트");
			set3 = new BarDataSet(yVals3, "총 봉사 기부내역");

			set1.setDrawIcons(false);
			set2.setDrawIcons(false);
			set3.setDrawIcons(false);

			set1.setColors(colors1);
			set2.setColors(colors2);
			set3.setColors(colors3);

			ArrayList<IBarDataSet> dataSets = new ArrayList<>();
			dataSets.add(set1);
			dataSets.add(set2);
			dataSets.add(set3);

			BarData data = new BarData(dataSets);
			data.setValueTextSize(10f);
			//data.setValueTypeface(mTfLight);
			data.setBarWidth(barWidth);
			mHoizonChart.setData(data);
		}
	}

	private void setPieData(List<Section> sections,int totalPoint) {

		ArrayList<PieEntry> entries = new ArrayList<>();

		/**
		 * PieChart Entry
		 */
		for(Section section : sections){
			entries.add(new PieEntry((float) section.getPoint()/totalPoint,
					section.getName()));
		}

		PieDataSet dataSet = new PieDataSet(entries, "Election Results");

		dataSet.setDrawIcons(false);

		dataSet.setSliceSpace(3f);
		dataSet.setIconsOffset(new MPPointF(0, 40));
		dataSet.setSelectionShift(5f);

		// add a lot of colors

		ArrayList<Integer> colors = new ArrayList<>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		dataSet.setColors(colors);
		//dataSet.setSelectionShift(0f);

		PieData data = new PieData(dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.WHITE);
		//data.setValueTypeface(mTfLight);
		mChart.setData(data);

		// undo all highlights
		mChart.highlightValues(null);

		mChart.invalidate();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		tvX.setText("" + (mSeekBarX.getProgress()));
		tvY.setText("" + (mSeekBarY.getProgress()));
	}

	@Override
	public void onValueSelected(Entry e, Highlight h) {
		if (e == null)
			return;
		Log.i("VAL SELECTED",
				"Value: " + e.getY() + ", index: " + h.getX()
						+ ", DataSet index: " + h.getDataSetIndex());
	}

	@Override
	public void onNothingSelected() {
		Log.i("PieChart", "nothing selected");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
}
