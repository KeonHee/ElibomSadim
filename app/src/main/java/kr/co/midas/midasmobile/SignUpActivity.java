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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.registerId)
    EditText registerId;
    @BindView(R.id.registerPwd)
    EditText registerPwd;
    @BindView(R.id.registerAge)
    EditText registerAge;
    @BindView(R.id.registerPhone)
    EditText registerPhone;
    @BindView(R.id.registerRank)
    EditText registerRank;
    @BindView(R.id.registerImageView)
    ImageView registerImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        registerImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == registerImg){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }
}
