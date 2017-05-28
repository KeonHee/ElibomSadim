package kr.co.midas.midasmobile.tabbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.adapters.MyFragmentPagerAdapter;

public class MyHome extends Fragment implements OnTabChangeListener,
        OnPageChangeListener {

	@BindView(R.id.tabHost)
	TabHost tabHost;
	@BindView(R.id.viewPager)
	ViewPager viewPager;
	private MyFragmentPagerAdapter myViewPagerAdapter;
	private int i = 0;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.tabs_viewpager_layout, container, false);

		i++;
		ButterKnife.bind(this, view);
		// tabhost 설정
		this.initializeTabHost(savedInstanceState);
		// ViewPager 설정
		this.initializeViewPager();

		return view;
	}

	// fake content for tabhost
	class FakeContent implements TabContentFactory {
		private final Context mContext;

		public FakeContent(Context context) {
			mContext = context;
		}

		@Override
		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumHeight(0);
			v.setMinimumWidth(0);
			return v;
		}
	}

	private void initializeViewPager() {
		List<Fragment> fragments = new Vector<Fragment>();

		fragments.add(new RankFragment());
		fragments.add(new VoluntFragment());
		fragments.add(new TeamFragment());
		fragments.add(new DonateFragment());


		this.myViewPagerAdapter = new MyFragmentPagerAdapter(
				getChildFragmentManager(), fragments);

		this.viewPager.setAdapter(this.myViewPagerAdapter);
		this.viewPager.setOnPageChangeListener(this);

	}

	private void initializeTabHost(Bundle args) {

		tabHost.setup();

		List<String> tabnames = new ArrayList<>();
		tabnames.add("랭킹피드");
		tabnames.add("봉사활동");
		tabnames.add("팀리스트");
		tabnames.add("기부");

		for (int i = 0; i < 4; i++) {

			TabHost.TabSpec tabSpec;

			tabSpec = tabHost.newTabSpec(tabnames.get(i));
			tabSpec.setIndicator(tabnames.get(i));
			tabSpec.setContent(new FakeContent(getActivity()));
			tabHost.addTab(tabSpec);
		}
		tabHost.setOnTabChangedListener(this);
	}

	@Override
	public void onTabChanged(String tabId) {
		int pos = this.tabHost.getCurrentTab();
		this.viewPager.setCurrentItem(pos);

		HorizontalScrollView hScrollView = (HorizontalScrollView) view
				.findViewById(R.id.hScrollView);
		View tabView = tabHost.getCurrentTabView();
		int scrollPos = tabView.getLeft()
				- (hScrollView.getWidth() - tabView.getWidth()) / 2;
		hScrollView.smoothScrollTo(scrollPos, 0);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
		this.tabHost.setCurrentTab(position);
	}
}
