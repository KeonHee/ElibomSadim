package kr.co.midas.midasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.base.domain.ResponseData;
import kr.co.midas.midasmobile.base.domain.User;
import kr.co.midas.midasmobile.base.network.LoginService;
import kr.co.midas.midasmobile.base.utils.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kr.co.midas.midasmobile.base.define.Define.NOT_FOUND;
import static kr.co.midas.midasmobile.base.define.Define.OK;
import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_EMAIL_KEY;
import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_PW_KEY;
import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_SESSION_KEY;
import static kr.co.midas.midasmobile.base.define.Define.SHR_PREF_USER_ID_KEY;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LoginActivity";

    @BindView(R.id.editTextEmail)
    EditText editEmail;
    @BindView(R.id.editTextPwd)
    EditText editPwd;
    @BindView(R.id.loginImageView)
    ImageView loginImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 자동 로그인
        if(SharedPreferenceUtils.getBooleanPreference(getApplicationContext(),
                SHR_PREF_SESSION_KEY, false)){
            moveToPage(MainActivity.class);
        }

        ButterKnife.bind(this);
        loginImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == loginImg){
            String email = editEmail.getText().toString().trim();
            String pw = editPwd.getText().toString().trim();
            if(validateEmail(view,email) && validatePW(view,pw)){
                login(email,pw);
            }
        }

    }

    private void moveToPage(Class<?> destActivity){
        final Intent intent = new Intent(getApplicationContext(), destActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    private boolean validateEmail(View v, String email){
        if(!email.contains("@")){
            Snackbar.make(v, "잘못된 이메일 형식입니다.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }

        return true;
    }
    private boolean validatePW(View v, String pw){
        if(pw.length()<6){
            Snackbar.make(v, "6자리 이상의 비밀번호가 필요합니다!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
    }

    private void login(String email, String pw){
        LoginService loginService = LoginService.retrofit.create(LoginService.class);
        Call<ResponseData<User>> call = loginService.login(email,pw);
        call.enqueue(callback);
    }

    private Callback<ResponseData<User>> callback = new Callback<ResponseData<User>>() {
        @Override
        public void onResponse(Call<ResponseData<User>> call, Response<ResponseData<User>> response) {
            if (response.isSuccessful()){
                ResponseData<User> responseData = response.body();
                if(responseData.getCode()== OK){
                    long uid = responseData.getResult().getId();
                    Log.d(TAG, String.valueOf(uid));

                    // save uid
                    SharedPreferenceUtils.setLongPreference(getApplicationContext(), SHR_PREF_USER_ID_KEY, uid);

                    // session on
                    SharedPreferenceUtils.setBooleanPreference(getApplicationContext(),
                            SHR_PREF_SESSION_KEY, true);
                    // save user info
                    SharedPreferenceUtils.setStringPreference(getApplicationContext(),
                            SHR_PREF_EMAIL_KEY, editEmail.getText().toString().trim());
                    SharedPreferenceUtils.setStringPreference(getApplicationContext(),
                            SHR_PREF_PW_KEY, editPwd.getText().toString().trim());


                    moveToPage(MainActivity.class);
                }else if(responseData.getCode() == NOT_FOUND){
                    Log.e(TAG,"Not Found Error");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "없는 회원입니다.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    Log.e(TAG,"fail T_T");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "알수 없는 에러", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }else {
                Log.e(TAG, response.errorBody().toString());
                Snackbar.make(getWindow().getDecorView().getRootView(), "Login Error!", Snackbar.LENGTH_LONG)
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
}
