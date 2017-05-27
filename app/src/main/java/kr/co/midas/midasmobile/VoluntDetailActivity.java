package kr.co.midas.midasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017-05-27.
 */

public class VoluntDetailActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.detail_volun_img)
    ImageView volunImg;
    @BindView(R.id.detail_volun_max)
    TextView volunnMax;
    @BindView(R.id.detail_volun_date)
    TextView volunDate;
    @BindView(R.id.detail_volun_title)
    TextView volunTitle;
    @BindView(R.id.detail_volun_contents)
    TextView volunCont;
    @BindView(R.id.addVoluntary)
    TextView addVolun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntdetail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("voluntImg")).into(volunImg);
        volunnMax.setText(intent.getStringExtra("partici") + " / " + intent.getStringExtra("maxParti"));
        volunDate.setText(intent.getStringExtra("date"));
        volunCont.setText(intent.getStringExtra("contents"));
        volunTitle.setText(intent.getStringExtra("voluntTitle"));

        addVolun.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == addVolun){
            /** 서버에 신청 요청 보내기 */

        }
    }
}
