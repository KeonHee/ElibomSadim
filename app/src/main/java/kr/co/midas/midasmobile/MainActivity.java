package kr.co.midas.midasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


import butterknife.ButterKnife;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.domain.User;
import kr.co.midas.midasmobile.base.fragments.MyHome;
import kr.co.midas.midasmobile.base.network.LoginService;

import kr.co.midas.midasmobile.tabbar.MyHome;

import kr.co.midas.midasmobile.base.utils.SharedPreferenceUtils;

import kr.co.midas.midasmobile.side.MyInfoActivity;
import kr.co.midas.midasmobile.side.MyTeamActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kr.co.midas.midasmobile.base.define.Define.NOT_FOUND;
import static kr.co.midas.midasmobile.base.define.Define.OK;
import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_SESSION_KEY;
import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_USER_ID_KEY;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";

    private TabHost tabHost;
    private ViewPager viewPager;

    ImageView mNavAvatar;
    TextView mNavUserName;
    TextView mNavEmail;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        // Load User Info & BindView
        long uid = SharedPreferenceUtils.getLongPreference(getApplicationContext(),
                SHR_PREF_USER_ID_KEY, -1);
        if(uid!=-1){
            loadUserInfo(uid);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeaderView = navigationView.getHeaderView(0);

        mNavAvatar = (ImageView) navHeaderView.findViewById(R.id.nav_avatar_iv);
        mNavUserName = (TextView) navHeaderView.findViewById(R.id.nav_name_tv);
        mNavEmail = (TextView) navHeaderView.findViewById(R.id.nav_email_tv);

        MyHome main = new MyHome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, main).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            SharedPreferenceUtils.setBooleanPreference(getApplicationContext(), SHR_PREF_SESSION_KEY, false);

            final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_info) {
            moveToPage(MyInfoActivity.class);
        } else if (id == R.id.nav_donation) {
            moveToPage(MyInfoActivity.class);
        } else if (id == R.id.nav_team) {
            moveToPage(MyTeamActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void moveToPage(Class<?> destActivity){
        final Intent intent = new Intent(getApplicationContext(), destActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void loadUserInfo(long uid){
        LoginService loginService = LoginService.retrofit.create(LoginService.class);
        Call<ResponseData<User>> call =  loginService.getUserInfo(uid);
        call.enqueue(callback);
    }

    private Callback<ResponseData<User>> callback = new Callback<ResponseData<User>>() {
        @Override
        public void onResponse(Call<ResponseData<User>> call, Response<ResponseData<User>> response) {

            if(response.isSuccessful()){
                ResponseData<User> responseData = response.body();
                if(responseData.getCode()== OK){
                    mUser = responseData.getResult();
                    bindViewNavHeader(
                            mUser.getAvatarUrl(), mUser.getUserName(),mUser.getEmail());

                }else if(responseData.getCode() == NOT_FOUND){
                    Log.e(TAG,"Not Found Error");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "없는 회원입니다.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    Log.e(TAG,"fail T_T");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "알수 없는 에러", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }else{
                Log.e(TAG, response.errorBody().toString());
                Snackbar.make(getWindow().getDecorView().getRootView(), "서버 에러!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseData<User>> call, Throwable t) {
            Log.e(TAG,t.getMessage());
            Snackbar.make(getWindow().getDecorView().getRootView(), "네트워크 에러", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };

    private void bindViewNavHeader(String avatar, String name, String email){

        //mNavAvatar
        mNavUserName.setText(name);
        mNavEmail.setText(email);
    }


}
