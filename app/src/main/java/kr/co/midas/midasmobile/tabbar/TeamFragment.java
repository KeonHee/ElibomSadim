package kr.co.midas.midasmobile.tabbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.domain.Team;
import kr.co.midas.midasmobile.base.network.TeamService;
import kr.co.midas.midasmobile.tabbar.adapters.TeamListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamFragment extends Fragment {
	private RecyclerView teamView;
	private TeamListAdapter teamListAdapter;
	private LinearLayoutManager linearLayoutManager;
	private List<Team> teamObjects;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_team, container, false);
		initViews(view);
		return view;
	}

	private void initViews(View view){
		teamView = (RecyclerView) view.findViewById(R.id.teamRecycle);
		teamView.setHasFixedSize(true);
		teamObjects = new ArrayList<>();
		linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		teamView.setLayoutManager(linearLayoutManager);

		teamListAdapter = new TeamListAdapter(view.getContext(), teamObjects);
		teamView.setAdapter(teamListAdapter);
		loadData(0);


	}

	private void loadData(int page){
		TeamService teamService = TeamService.retrofit.create(TeamService.class);
		Call<ResponseData<List<Team>>> call = teamService.getTeamList(page);
		call.enqueue(new Callback<ResponseData<List<Team>>>() {
			@Override
			public void onResponse(Call<ResponseData<List<Team>>> call, Response<ResponseData<List<Team>>> response) {
				if(response.isSuccessful()){
					if(response.body().getCode() == 200){
						List<Team> teamList = response.body().getResult();
						teamListAdapter.setTeamList(teamList);
						Log.e("팀", String.valueOf(teamList));
					}

				}else{

				}
			}

			@Override
			public void onFailure(Call<ResponseData<List<Team>>> call, Throwable t) {

			}
		});
	}

}
