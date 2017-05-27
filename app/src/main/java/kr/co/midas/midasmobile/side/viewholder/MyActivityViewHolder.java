package kr.co.midas.midasmobile.side.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Record;
import kr.co.midas.midasmobile.base.domain.Voluntary;


public class MyActivityViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.title_tv)
    TextView mTitle;
    @BindView(R.id.location_tv)
    TextView mLocation;
    @BindView(R.id.vol_date_tv)
    TextView mDate;
    @BindView(R.id.section_tv)
    TextView mSection;
    @BindView(R.id.point_tv)
    TextView mPoint;

    private Context mContext;

    public MyActivityViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.my_activity_list,parent,false));

        mContext=context;

        ButterKnife.bind(this, itemView);
    }

    public void onBind(Record record){

        Voluntary voluntary = record.getVoluntary();
        Date date = voluntary.getVoluntary_date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = transFormat.format(date);

        mTitle.setText(voluntary.getTitle());
        mLocation.setText(voluntary.getLocation());
        mDate.setText(dateStr);
        mSection.setText(voluntary.getSection());
        mPoint.setText(String.valueOf(voluntary.getVoluntary_time()*1000));

    }


}
