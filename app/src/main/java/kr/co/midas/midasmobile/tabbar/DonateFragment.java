package kr.co.midas.midasmobile.tabbar;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.define.Define;
import kr.co.midas.midasmobile.base.domain.Report;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.network.ReportService;
import kr.co.midas.midasmobile.base.utils.SharedPreferenceUtils;
import kr.co.midas.midasmobile.tabbar.adapters.ReportListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DonateFragment extends Fragment implements View.OnClickListener {

	private RecyclerView donateRecycle;
	private ReportListAdapter reportAdapter;
	private LinearLayoutManager linearLayoutManager;
	private List<Report> reportObjects;
	private Dialog donateDialog;
	private ImageView arrowUp, arrowDown;
	private TextView currentPoint, setPoint, givePoint, showDonate;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_donate, container, false);
		initViews(view);
		loadData(0, "all");
		return view;
	}

	private void initViews(View view){

		currentPoint = (TextView) view.findViewById(R.id.myCurrentPoint);
		long point = SharedPreferenceUtils.getLongPreference(getContext(), Define.SHR_PREF_CUR_POINT_KEY,-1);
		currentPoint.setText("현재 포인트 : " + String.valueOf(point));
		showDonate = (TextView) view.findViewById(R.id.showDonate);
		showDonate.setOnClickListener(this);

		donateDialog = new Dialog(view.getContext());
		donateDialog.setContentView(R.layout.donate_dialog);
		arrowUp = (ImageView) donateDialog.findViewById(R.id.point_up);
		arrowUp.setOnClickListener(this);
		arrowDown = (ImageView) donateDialog.findViewById(R.id.point_down);
		arrowDown.setOnClickListener(this);
		setPoint = (TextView) donateDialog.findViewById(R.id.set_point);
		givePoint = (TextView) donateDialog.findViewById(R.id.givePoint);
		givePoint.setOnClickListener(this);

		donateRecycle = (RecyclerView) view.findViewById(R.id.donateRecycle);
		donateRecycle.setHasFixedSize(true);
		reportObjects = new ArrayList<>();
		linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		donateRecycle.setLayoutManager(linearLayoutManager);

		reportAdapter = new ReportListAdapter(view.getContext(), reportObjects);
		donateRecycle.setAdapter(reportAdapter);

	}

	private void loadData(int page, String string){
		ReportService reportService = ReportService.retrofit.create(ReportService.class);
		Call<ResponseData<List<Report>>> call = reportService.getReportAll(page, string);
		call.enqueue(new Callback<ResponseData<List<Report>>>() {
			@Override
			public void onResponse(Call<ResponseData<List<Report>>> call, Response<ResponseData<List<Report>>> response) {
				if(response.isSuccessful()){
					if(response.body().getCode() == 200){
						List<Report> reportList = response.body().getResult();
						reportAdapter.setReportList(reportList);
						Log.e("팀", String.valueOf(reportList));
					}

				}else{

				}
			}

			@Override
			public void onFailure(Call<ResponseData<List<Report>>> call, Throwable t) {

			}
		});
	}

	@Override
	public void onClick(View view) {

		if(view == showDonate){
			donateDialog.show();
		}
		else if (view == arrowUp){
			String curStr = setPoint.getText().toString();
			int value = Integer.parseInt(curStr) + 1000;
			setPoint.setText(String.valueOf(value));

		}
		else if (view == arrowDown){
			String curStr = setPoint.getText().toString();
			int value = Integer.parseInt(curStr) - 1000;
			if(value >= 0)
				setPoint.setText(String.valueOf(value));

		}
		else if (view == givePoint){
			setPoint.setText("1000");
//			int point = Integer.parseInt(setPoint.getText().toString());
//			Log.e("기부포인트", String.valueOf());
			donateDialog.dismiss();

		}
	}
}
