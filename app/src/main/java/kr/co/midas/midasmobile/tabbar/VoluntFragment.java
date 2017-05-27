package kr.co.midas.midasmobile.tabbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.VoluntDetailActivity;
import kr.co.midas.midasmobile.tabbar.adapters.VoluntListAdapter;
import kr.co.midas.midasmobile.tabbar.objects.VoluntObject;


public class VoluntFragment extends Fragment {

	private RecyclerView voluntView;
	private List<VoluntObject> voluntObjects;
	private VoluntListAdapter voluntAdapter;
	private GridLayoutManager gridLayoutManager;
	private SearchView searchView;
	private SearchView.OnQueryTextListener queryTextListener;

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

		setHasOptionsMenu(true);
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

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_volunt_search, menu);
		MenuItem searchItem = menu.findItem(R.id.menu_volunt_search);
		SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

		if (searchItem != null) {
			searchView = (SearchView) searchItem.getActionView();
		}
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

			queryTextListener = new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextChange(String newText) {
					Log.i("onQueryTextChange", newText);

					return true;
				}
				@Override
				public boolean onQueryTextSubmit(String query) {
					Log.i("onQueryTextSubmit", query);
					/** VoluntDetailActivity에서 보여주면 됩니다 */
					Intent intent = new Intent(getActivity().getApplicationContext(), VoluntDetailActivity.class);
					intent.putExtra("voluntTitle", query);
					startActivity(intent);
					return true;
				}
			};
			searchView.setOnQueryTextListener(queryTextListener);
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_volunt_search:
				// Not implemented here
				return false;
			default:
				break;
		}
		searchView.setOnQueryTextListener(queryTextListener);
		return super.onOptionsItemSelected(item);
	}
}
