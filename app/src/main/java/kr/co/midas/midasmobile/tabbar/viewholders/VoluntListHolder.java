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
import kr.co.midas.midasmobile.tabbar.adapters.VoluntListAdapter;
import kr.co.midas.midasmobile.tabbar.objects.VoluntObject;

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

    public void onBind(Context context, VoluntObject voluntObject){
        Glide.with(context).load(voluntObject.getImgUrl()).into(volunImg);
        volunTitle.setText(voluntObject.getImgTitle());
        ptcipants.setText(voluntObject.getParticipants());
    }

    @Override
    public void onClick(View view) {
        if (view == voluntCard){
            Intent intent = new Intent(view.getContext(), VoluntDetailActivity.class);
            intent.putExtra("volutTitle", VoluntListAdapter.voluntList.get(getAdapterPosition()).getImgTitle());
            view.getContext().startActivity(intent);
        }

    }
}
