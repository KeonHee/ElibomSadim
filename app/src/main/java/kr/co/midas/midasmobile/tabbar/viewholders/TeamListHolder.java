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
import kr.co.midas.midasmobile.TeamDetailActivity;
import kr.co.midas.midasmobile.tabbar.adapters.TeamListAdapter;
import kr.co.midas.midasmobile.tabbar.objects.TeamObject;

/**
 * Created by user on 2017-05-23.
 */

public class TeamListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.teamlogo)
    ImageView teamLogo;
    @BindView(R.id.team_name)
    TextView teamName;
    @BindView(R.id.team_descript)
    TextView teamDesc;
    @BindView(R.id.team_point)
    TextView teamPoint;
    @BindView(R.id.team_card)
    CardView teamCard;


    public TeamListHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        setPrefTextView(teamDesc);
        teamCard.setOnClickListener(this);
    }

    private void setPrefTextView(TextView textView){
        textView.setMaxLines(3);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public void onBind(Context context, TeamObject teamObject){
        Glide.with(context).load(teamObject.getLogoUrl()).into(teamLogo);
        teamName.setText(teamObject.getTeamName());
        teamDesc.setText(teamObject.getDescript());
        teamPoint.setText(String.valueOf(teamObject.getTeamPoint()));
    }

    @Override
    public void onClick(View view) {
        if(view == teamCard){
            Intent intent = new Intent(view.getContext(), TeamDetailActivity.class);
            intent.putExtra("teamName", TeamListAdapter.teamList.get(getAdapterPosition()).getTeamName());
            view.getContext().startActivity(intent);
        }
    }
}
