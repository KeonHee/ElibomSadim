package kr.co.midas.midasmobile.tabbar.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.VoluntDetailActivity;
import kr.co.midas.midasmobile.base.domain.Voluntary;
import kr.co.midas.midasmobile.tabbar.adapters.VoluntListAdapter;

/**
 * Created by user on 2017-05-27.
 */

public class VoluntListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.volunImg)
    ImageView volunImg;
    @BindView(R.id.volunTitle)
    TextView volunTitle;
    @BindView(R.id.ptcipants)
    TextView ptcipants;
    @BindView(R.id.volunt_card)
    CardView voluntCard;

    public VoluntListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        setPrefTextView(volunTitle);
        voluntCard.setOnClickListener(this);
    }

    private void setPrefTextView(TextView textView){

        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public void onBind(Context context, Voluntary voluntObject){
        Glide.with(context).load(voluntObject.getImgPath()).into(volunImg);
        volunTitle.setText(voluntObject.getTitle());
//        ptcipants.setText(voluntObject.getCurrentParticipants());
    }

    @Override
    public void onClick(View view) {
        if (view == voluntCard){
            Intent intent = new Intent(view.getContext(), VoluntDetailActivity.class);
            intent.putExtra("voluntImg", VoluntListAdapter.voluntList.get(getAdapterPosition()).getImgPath());
            intent.putExtra("volutTitle", VoluntListAdapter.voluntList.get(getAdapterPosition()).getTitle());
            intent.putExtra("maxParti", VoluntListAdapter.voluntList.get(getAdapterPosition()).getMaxParticipants());
            intent.putExtra("partici", VoluntListAdapter.voluntList.get(getAdapterPosition()).getCurrentParticipants());
            intent.putExtra("contents", VoluntListAdapter.voluntList.get(getAdapterPosition()).getContents());
            intent.putExtra("date", VoluntListAdapter.voluntList.get(getAdapterPosition()).getVoluntary_date());

            view.getContext().startActivity(intent);
        }

    }
}
