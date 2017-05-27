package kr.co.midas.midasmobile.tabbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.tabbar.adapters.TeamListAdapter;
import kr.co.midas.midasmobile.tabbar.objects.TeamObject;


public class TeamFragment extends Fragment {
	private RecyclerView teamView;
	private TeamListAdapter teamListAdapter;
	private LinearLayoutManager linearLayoutManager;
	private List<TeamObject> teamObjects;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_team, container, false);
		initViews(view);
		firstPage(view);
		return view;
	}

	private void initViews(View view){
		teamView = (RecyclerView) view.findViewById(R.id.teamRecycle);
		teamView.setHasFixedSize(true);
		teamObjects = new ArrayList<>();
		linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		teamView.setLayoutManager(linearLayoutManager);

	}

	private void firstPage(View view){
		/** 서버에서 데이터 받아와서 추가해주면 된다*/
		teamObjects.add(new TeamObject("http://52.79.189.34/story/cat1.jpg", "나","나를 맞춰봐" ,1));
		teamObjects.add(new TeamObject("http://52.79.189.34/story/cat2.jpg", "나","나를 맞춰봐" ,1));
		teamObjects.add(new TeamObject("http://52.79.189.34/story/cat3.jpg", "나","나를 맞춰봐" ,1));
		teamObjects.add(new TeamObject("http://52.79.189.34/story/cat4.jpg", "나","나를 맞춰봐" ,1));
		teamObjects.add(new TeamObject("http://52.79.189.34/story/cat5.jpg", "나","나를 맞춰봐" ,1));

		teamListAdapter = new TeamListAdapter(view.getContext(), teamObjects);
		teamView.setAdapter(teamListAdapter);
	}
}
