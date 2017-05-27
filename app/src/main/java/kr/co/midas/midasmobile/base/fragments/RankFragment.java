package kr.co.midas.midasmobile.base.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.midas.midasmobile.R;


public class RankFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_rank, container, false);
		
		return v;
	}
}
