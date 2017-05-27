package kr.co.midas.midasmobile.tabbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.tabbar.adapters.VoluntListAdapter;
import kr.co.midas.midasmobile.tabbar.objects.VoluntObject;


public class VoluntFragment extends Fragment {

	private RecyclerView voluntView;
	private List<VoluntObject> voluntObjects;
	private VoluntListAdapter voluntAdapter;
	private GridLayoutManager gridLayoutManager;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_volunt, container, false);
		initViews(view);
		firstPage(view);
		return view;
	}

	private void initViews(View view){
		voluntView = (RecyclerView) view.findViewById(R.id.voluntRecycle);
		voluntView.setHasFixedSize(true);
		voluntObjects = new ArrayList<>();
		gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
		voluntView.setLayoutManager(gridLayoutManager);
	}

	private void firstPage(View view){
		voluntObjects.add(new VoluntObject("http://52.79.189.34/story/cat1.jpg", "야", "2"));
		voluntObjects.add(new VoluntObject("http://52.79.189.34/story/cat2.jpg", "야2", "2"));
		voluntObjects.add(new VoluntObject("http://52.79.189.34/story/cat3.jpg", "야3", "2"));
		voluntObjects.add(new VoluntObject("http://52.79.189.34/story/cat4.jpg", "야4", "2"));
		voluntObjects.add(new VoluntObject("http://52.79.189.34/story/cat5.jpg", "야5", "2"));
		voluntAdapter = new VoluntListAdapter(view.getContext(), voluntObjects);
		voluntView.setAdapter(voluntAdapter);
	}
}
