package kr.co.midas.midasmobile.side.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Donation;

public class MyDonationViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.receiver_tv)
    TextView mReceiver;
    @BindView(R.id.send_date_tv)
    TextView mDate;
    @BindView(R.id.point_tv)
    TextView mPoint;

    private Context mContext;

    public MyDonationViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.my_donation_list, parent, false));

        mContext = context;

        ButterKnife.bind(this, itemView);
    }

    public void onBind(Donation donation) {

//        Date date = donation.getDonate_at();
//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
//        String dateStr = transFormat.format(date);

        mReceiver.setText(donation.getRecipient());
        mDate.setText(donation.getDonate_at());
        mPoint.setText(String.valueOf(donation.getPoint()));

    }
}
