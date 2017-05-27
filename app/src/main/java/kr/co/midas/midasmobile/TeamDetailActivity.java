package kr.co.midas.midasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017-05-27.
 */

public class TeamDetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_team_img)
    ImageView detail_img;
    @BindView(R.id.detail_team_point)
    TextView detail_team_point;
    @BindView(R.id.detail_team_name)
    TextView detail_team_name;
    @BindView(R.id.detail_team_descript)
    TextView detail_team_desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamdetail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("imgUrl")).into(detail_img);
        detail_team_point.setText(String.valueOf(intent.getIntExtra("teamPoint", 0)));
        detail_team_name.setText(intent.getStringExtra("teamName"));
        detail_team_desc.setText(intent.getStringExtra("teamDesc"));
    }
}
