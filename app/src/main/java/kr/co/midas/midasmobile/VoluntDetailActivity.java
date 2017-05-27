package kr.co.midas.midasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 2017-05-27.
 */

public class VoluntDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntdetail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("voluntTitle");

    }
}
