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
import kr.co.midas.midasmobile.ReportDetailActivity;
import kr.co.midas.midasmobile.base.domain.Report;
import kr.co.midas.midasmobile.tabbar.adapters.ReportListAdapter;

/**
 * Created by user on 2017-05-28.
 */

public class ReportListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.report_img)
    ImageView reportImg;
    @BindView(R.id.report_date)
    TextView reportDate;
    @BindView(R.id.report_loc)
    TextView reportLoc;
    @BindView(R.id.report_title)
    TextView reportTitle;
    @BindView(R.id.report_card)
    CardView reportCard;

    public ReportListHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        setPrefTextView(reportTitle);
        reportCard.setOnClickListener(this);
    }

    private void setPrefTextView(TextView textView){
        textView.setMaxLines(3);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public void onBind(Context context, Report report){
        Glide.with(context).load(report.getImgUrl()).into(reportImg);
        reportDate.setText(String.valueOf(report.getDate()));
        reportLoc.setText(report.getLocation());
        reportTitle.setText(report.getTitle());
    }

    @Override
    public void onClick(View view) {
        if(view == reportCard){
            Intent intent = new Intent(view.getContext(), ReportDetailActivity.class);
            intent.putExtra("reportTitle", ReportListAdapter.reportList.get(getAdapterPosition()).getTitle());
            view.getContext().startActivity(intent);
        }
    }

}
