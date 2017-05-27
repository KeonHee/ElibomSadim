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
 * Created by user on 2017-05-28.
 */

public class ReportDetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_report_img)
    ImageView reportImg;
    @BindView(R.id.detail_report_point)
    TextView reportPoint;
    @BindView(R.id.detail_report_date)
    TextView reportDate;
    @BindView(R.id.detail_report_loc)
    TextView reportLoc;
    @BindView(R.id.detail_report_title)
    TextView reportTitle;
    @BindView(R.id.detail_report_contents)
    TextView reportCont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportdetail);

        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("reportImg")).into(reportImg);
        reportPoint.setText(intent.getStringExtra("reportPoint"));
        reportDate.setText(intent.getStringExtra("reportDate"));
        reportLoc.setText(intent.getStringExtra("reportLoc"));
        reportCont.setText(intent.getStringExtra("reportContents"));
        reportTitle.setText(intent.getStringExtra("reportTitle"));

        ButterKnife.bind(this);

    }
}
