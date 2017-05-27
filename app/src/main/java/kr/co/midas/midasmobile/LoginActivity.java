package kr.co.midas.midasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017-05-27.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.editTextId)
    EditText editId;
    @BindView(R.id.editTextPwd)
    EditText editPwd;
    @BindView(R.id.loginImageView)
    ImageView loginImg;
    @BindView(R.id.moveSignUp)
    ImageView moveSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginImg.setOnClickListener(this);
        moveSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == loginImg){
            moveToPage(MainActivity.class);
        }
        else if (view == moveSignUp)
            moveToPage(SignUpActivity.class);
    }

    private void moveToPage(Class<?> destActivity){
        final Intent intent = new Intent(getApplicationContext(), destActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
