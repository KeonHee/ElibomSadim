package kr.co.midas.midasmobile.tabbar;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Report;
import kr.co.midas.midasmobile.tabbar.adapters.ReportListAdapter;


public class DonateFragment extends Fragment implements View.OnClickListener {

	private RecyclerView donateRecycle;
	private ReportListAdapter reportAdapter;
	private LinearLayoutManager linearLayoutManager;
	private List<Report> reportObjects;
	private Dialog donateDialog;
	private RelativeLayout tabPoint;
	private TextView currentPoint, setPoint, givePoint, showDonate;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_donate, container, false);
		initViews(view);
		firstPage(view);
		return view;
	}

	private void initViews(View view){

		currentPoint = (TextView) view.findViewById(R.id.myCurrentPoint);
		currentPoint.setText("현재 포인트 : ");
		showDonate = (TextView) view.findViewById(R.id.showDonate);
		showDonate.setOnClickListener(this);
//		likeButtonView = (LikeButtonView) view.findViewById(R.id.donateBtn);
//		likeButtonView.setOnClickListener(this);

		donateDialog = new Dialog(view.getContext());
		donateDialog.setContentView(R.layout.donate_dialog);
		tabPoint = (RelativeLayout) donateDialog.findViewById(R.id.point_tap);
		tabPoint.setOnClickListener(this);
		setPoint = (TextView) donateDialog.findViewById(R.id.set_point);
		givePoint = (TextView) donateDialog.findViewById(R.id.givePoint);
		givePoint.setOnClickListener(this);

		donateRecycle = (RecyclerView) view.findViewById(R.id.donateRecycle);
		donateRecycle.setHasFixedSize(true);
		reportObjects = new ArrayList<>();
		linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		donateRecycle.setLayoutManager(linearLayoutManager);

	}

	private void firstPage(View view){
		/** 서버에서 데이터 받아와서 추가해주면 된다*/
		reportObjects.add(new Report(1, "진행", "나" ,new Date(),"서울","80","재밌었다","http://52.79.189.34/story/cat1.jpg"));


		reportAdapter = new ReportListAdapter(view.getContext(), reportObjects);
		donateRecycle.setAdapter(reportAdapter);
	}

	@Override
	public void onClick(View view) {

		if(view == showDonate){
			donateDialog.show();
		}
		else if (view == tabPoint){
			String curStr = setPoint.getText().toString();
			int value = Integer.parseInt(curStr) + 1000;
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
